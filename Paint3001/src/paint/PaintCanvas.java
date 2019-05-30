package paint;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;

public class PaintCanvas extends Canvas {

    private double size;
    private GraphicsContext gc;
    private ShapeType shapeType;
    private ArrayList<Action> actions = new ArrayList<>();
    private ArrayList<Action> undoActions = new ArrayList<>();
    private ArrayList<Action> redoActions = new ArrayList<>();
    private UndoHistoryListView<String> undoStacks;
    private Color fillColour;
    private Color penColour;
    private boolean polyEdit;

    @Override
    public double prefWidth(double height) {
        return getWidth();
    }

    @Override
    public double prefHeight(double width) {
        return getWidth();
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    /**
     * Creates a square canvas of certain size given its size in pixels
     * and also undo stack GUI component which it updates after
     * a new shape is drawn.
     *
     * @param pixels number representing the size of the canvas
     * @param undoStack list view representing the actions of the user
     */
    public PaintCanvas(int pixels, UndoHistoryListView<String> undoStack) {
        this.undoStacks = undoStack;
        this.size = pixels;
        super.setWidth(pixels);
        super.setHeight(pixels);

        // Make canvas resizable
        widthProperty().addListener(evt -> redraw());
        heightProperty().addListener(evt -> redraw());

        // Set the initial pen settings
        this.gc = this.getGraphicsContext2D();
        this.shapeType = ShapeType.LINE;
        this.fillColour = Color.TRANSPARENT;
        this.penColour = Color.BLACK;
        this.polyEdit = false;

        // Let the user draw the shapes with the mouse events
        addMouseClickEventHandler();
        addMouseMoveEventHandler();
        addMousePressedEventHandler();
        addMouseDraggedEventHandler();
        addMouseReleasedEventHandler();
    }

    /**
     * Initialise shapes when user makes first mouse click on canvas
     */
    public void addMousePressedEventHandler(){
        addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("Current square size is: "+size);
                        double x = event.getX()/size;
                        double y = event.getY()/size;

                        System.out.println("X: " + event.getX() + "\nY: " + event.getY());

                        if(shapeType == ShapeType.RECTANGLE){
                            Shape rect = new PaintRectangle();
                            initShape(rect, x, y);
                        }
                        else if(shapeType == ShapeType.ELLIPSE){
                            Shape ellipse = new PaintEllipse();
                            initShape(ellipse, x, y);
                        }
                        else if(shapeType == ShapeType.LINE){
                            Shape line = new PaintLine();
                            initShape(line, x, y);
                        }
                    }
                });
    }

    /**
     * Add x and y coordinates to the shapes when the user continues to
     * click on the canvas and draw when necessary
     */
    private void addMouseClickEventHandler(){
        addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(shapeType == ShapeType.PLOT){
                            Shape plot = new PaintPlot();
                            plot.addXCoord(event.getX()/size);
                            plot.addYCoord(event.getY()/size);
                            actions.add(plot);
                            undoStacks.updateHistoryListView(actions);

                            plot.draw(gc, size);
                        }
                        else if(shapeType == ShapeType.POLYGON && !polyEdit){
                            System.out.println("poly created");
                            Shape polygon = new PaintPolygon();
                            polygon.addXCoord(event.getX()/size);
                            polygon.addYCoord(event.getY()/size);
                            polygon.addXCoord(event.getX()/size);
                            polygon.addYCoord(event.getY()/size);
                            actions.add(polygon);
                            polyEdit = true;

                            polygon.draw(gc, size);
                        }
                        else if(shapeType == ShapeType.POLYGON && polyEdit){
                            System.out.println("poly edited");
                            Shape polygon = (Shape) actions.get(actions.size() - 1);

                            polygon.addXCoord(event.getX()/size);
                            polygon.addYCoord(event.getY()/size);
                            polygon.draw(gc, size);

                            actions.set(actions.size() - 1, polygon);
                            redraw();
                        }
                    }
                });

    }

    /**
     * Add event to let the user to see change to their polygon in real time
     */
    public void addMouseMoveEventHandler(){
        addEventHandler(MouseEvent.MOUSE_MOVED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(shapeType == ShapeType.POLYGON && polyEdit){
                    PaintPolygon polygon = (PaintPolygon) actions.get(actions.size() - 1);
                    if(polygon.getXCoords().size() > 1) {
                        System.out.println("poly Live");
                        polygon.setLastCoord(event.getX()/size, event.getY()/size);
                        redraw();
                        polygon.draw(gc, size);
                    }
                }
            }
        });
    }


    /**
     * Add event to let users see their RECTANGLE, ELLIPSE
     * and LINE shapes in real time as they draw
     */
    public void addMouseDraggedEventHandler(){
        addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        double x = event.getX()/size;
                        double y = event.getY()/size;

                        if(shapeType == ShapeType.RECTANGLE || shapeType == ShapeType.ELLIPSE || shapeType == ShapeType.LINE){
                            redrawShape(x, y);
                        }

                        System.out.println(event.getX());
                        System.out.println(event.getY());

                        redraw();
                    }
                });
    }

    /**
     * Add event so when user stops drawing, a new shape is added to the list of actions.
     * At the same time, the undo history stack is updated to show user new listing
     * and the previous history stacks are erased.
     */
    public void addMouseReleasedEventHandler(){
        addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        double x = event.getX()/size;
                        double y = event.getY()/size;
                        if(shapeType == ShapeType.RECTANGLE || shapeType == ShapeType.ELLIPSE){
                            finalShape(x, y);
                        }
                        undoStacks.updateHistoryListView(actions);
                        redraw();
                        undoActions.clear();
                        redoActions.clear();
                    }
                });
    }

    /**
     * Returns the graphics context of the canvas
     * @return gc
     */
    public GraphicsContext getGraphicsContext() {
        return gc;
    }

    /**
     * Returns the current shape type of the canvas
     * @return ShapeType
     */
    public ShapeType getShapeType() {
        return this.shapeType;
    }

    /**
     * Sets the shape type of the canvas
     * @param shapeType ShapeType
     */
    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    /**
     * Returns list of actions of the canvas
     * @return ArrayList(Action)
     */
    public ArrayList<Action> getActions() {
        return this.actions;
    }

    /**
     * Given an action object, an addition is made to the
     * canvas's current list of actions with the specified action
     * @param action an action object such as SetFill, SetLine, PaintPlot and so on.
     */
    public void addToActions(Action action) {
        actions.add(action);
    }

    /**
     * Given the colour, the canvas's fill colour property is
     * set to the specified colour
     * @param colour colour object representing shape fill such as a HEX colour value
     */
    public void setFillColour(Color colour){
        this.fillColour = colour;
    }

    /**
     * Given the colour, the canvas's pen colour property is
     * set to the specified colour
     * @param colour colour object for the pen such as a HEX colour value
     */

    public void setPenColour(Color colour){
        this.penColour = colour;
    }

    /**
     * Given a shape object, and initial x and y coordinate, an initial shape
     * is created with 2 pairs of coordinates and added to the list.
     * @param shape shape object such as PaintPlot, PaintRectangle
     * @param x number representing the initial x coordinate of the shape
     *         as a ratio relative to the canvas size
     * @param y number representing the initial y coordinate of the shape
     *          as a ratio relative to the canvas size
     */
    private void initShape(Shape shape, double x, double y){
        shape.addXCoord(x);
        shape.addYCoord(y);
        shape.addXCoord(x);
        shape.addYCoord(y);

        actions.add(shape);
    }

    /**
     * Given x and y coordinates the second lot of coordinates of a
     * shape such as RECTANGLE and ELLIPSE are redrawn
     * @param x number of the second x coordinate as a ratio
     * @param y number of the second y coordinate as a ratio
     */
    private void redrawShape(double x, double y){
        Shape shape = (Shape) actions.get(actions.size() - 1);

        shape.setX2Coord(x);
        shape.setY2Coord(y);
        shape.draw(gc, size);
    }

    private void finalShape(double x, double y){
        Shape shape = (Shape) actions.get(actions.size() - 1);

        if (x < shape.getXCoords().get(0)) {
            double tempX = shape.getXCoords().get(0);
            shape.setX1Coord(x);
            shape.setX2Coord(tempX);
        }

        if (y < shape.getYCoords().get(0)) {
            double tempY = shape.getYCoords().get(0);
            shape.setY1Coord(y);
            shape.setY2Coord(tempY);
        }


        actions.set(actions.size() - 1, shape);
        shape.printInstruction();
    }

    /**
     * Closes the polygon shape using its initial coordinates.
     */
    public void completePolygon(){
        if(polyEdit){
            this.polyEdit = false;
            Shape polygon = (Shape) actions.get(actions.size() - 1);
            polygon.addXCoord(polygon.getXCoords().get(0));
            polygon.addYCoord(polygon.getYCoords().get(0));

            actions.set(actions.size() - 1, polygon);
            polygon.printInstruction();
        }
    }

    /**
     * Erases all of the actions stored in the actions property.
     */
    public void clearActions(){
        this.actions.clear();
    }

    /**
     * Updates the actions property.
     * @param actions new actions list which usually has additions
     *                or removals
     */
    public void updateActions(ArrayList<Action> actions) {
          clearActions();
          for (int a = 0; a < actions.size(); a++){
              this.actions.add(actions.get(a));
          };
      }

    /**
     * Updates the actions property by deleting the most recent
     * action object and redraws the canvas while also updating
     * the undo stack.
     */
    public void undoAction() {

        if (actions.size() > 0) {
            undoActions.add(actions.get(actions.size() - 1));
            ArrayList<Action> newActions = new ArrayList<Action>();
            for (int a = 0; a< actions.size()-1; a++) {
                newActions.add(actions.get(a));
            }

            System.out.println(undoActions);
            updateActions(newActions);
            redraw();
            undoStacks.updateHistoryListView(newActions);
        }


    }

    /**
     * Given the new undo list the undoActions property is set
     * the the specified values.
     * @param newUndoList new undoList which has either additions
     *                    or removals from the current undoActions
     */
    public void updateUndoList(ArrayList<Action> newUndoList){
        undoActions.clear();
        for (int a = 0; a < newUndoList.size(); a++) {
            undoActions.add(newUndoList.get(a));
        }
    }


    /**
     * Moves the most recent action object stored in the undoActions back to
     * masters action list and redraws the canvas.
     */
    public void redoAction() {

        if (undoActions.size() > 0) {
            ArrayList<Action> newActions = new ArrayList<Action>();
            ArrayList<Action> newUndoList = new ArrayList<Action>();
            //newActions = actions;

            for (int a = 0; a < actions.size(); a++) {
                newActions.add(actions.get(a));
            }
            newActions.add(undoActions.get(undoActions.size() - 1));

            System.out.println(undoActions);
            updateActions(newActions);
            redraw();
            undoStacks.updateHistoryListView(newActions);

            //update undoList
            for (int u = 0; u < undoActions.size() - 1; u++) {
                newUndoList.add(undoActions.get(u));
            }

            updateUndoList(newUndoList);



        }
    }

    /**
     * Redraw the canvas using the current actions list.
     */
    public void redraw(){
        gc.clearRect(0,0, size, size);

        if (getWidth() <= getHeight()) {
            size = getWidth();
        }
        else {
            size = getHeight();
        }
        System.out.println("Pixels are " + size);

        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, size, size);
        gc.setFill(Color.TRANSPARENT);
        gc.setStroke(Color.BLACK);

        for(int index = 0; index < actions.size(); index++){

            actions.get(index).draw(gc, size);
        }
    }
}
