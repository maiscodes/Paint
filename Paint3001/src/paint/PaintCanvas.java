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

    // trying to make it resizable

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


    public PaintCanvas(int pixels, UndoHistoryListView<String> undoStack) {
        this.undoStacks = undoStack;
        this.size = pixels;
        super.setWidth(pixels);
        super.setHeight(pixels);
        widthProperty().addListener(evt -> redraw());
        heightProperty().addListener(evt -> redraw());

        this.gc = this.getGraphicsContext2D();
        this.shapeType = ShapeType.LINE;
        this.fillColour = Color.TRANSPARENT;
        this.penColour = Color.BLACK;
        this.polyEdit = false;

        //Canvas events
        addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if(shapeType == ShapeType.PLOT){
                            Shape plot = new PaintPlot();
                            plot.addXCoord(event.getX()/size);
                            plot.addYCoord(event.getY()/size);
                            actions.add(plot);

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

        addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        double x = event.getX()/size;
                        double y = event.getY()/size;
                        if(shapeType == ShapeType.RECTANGLE || shapeType == ShapeType.ELLIPSE){
                            finalShape(x, y);
                        }
                        undoStack.updateHistoryListView(actions);
                        redraw();
                        undoActions.clear();
                        redoActions.clear();
                    }
                });
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }

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

    public ShapeType getShapeType() {
        return this.shapeType;
    }

    public void setShapeType(ShapeType shapeType) {
        this.shapeType = shapeType;
    }

    public ArrayList<Action> getActions() {
        return this.actions;
    }

    public void addToActions(Action action) {
        actions.add(action);
    }

    public void setFillColour(Color colour){
        this.fillColour = colour;
    }

    public void setPenColour(Color colour){
        this.penColour = colour;
    }

    private void initShape(Shape shape, double x, double y){
        shape.addXCoord(x);
        shape.addYCoord(y);
        shape.addXCoord(x);
        shape.addYCoord(y);

        actions.add(shape);
    }

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

    public void updateActions(ArrayList<Action> actions) {
          this.actions.clear();
          for (int a = 0; a < actions.size(); a++){
              this.actions.add(actions.get(a));
          };
      }


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

    public void updateUndoList(ArrayList<Action> newUndoList){
        undoActions.clear();
        for (int a = 0; a < newUndoList.size(); a++) {
            undoActions.add(newUndoList.get(a));
        }
    }

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
