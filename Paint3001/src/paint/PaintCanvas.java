package paint;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.util.ArrayList;


public class PaintCanvas extends Canvas {

    private int pixels;
    private GraphicsContext gc;
    private ShapeType shapeType;
    private ArrayList<Action> actions = new ArrayList<>();
    private Color fillColour;
    private Color penColour;

    public PaintCanvas(int pixels, UndoHistoryListView<String> undoStack) {
        this.pixels = pixels;
        super.setWidth(pixels);
        super.setHeight(pixels);
        this.gc = this.getGraphicsContext2D();
        this.shapeType = ShapeType.LINE;
        this.fillColour = Color.TRANSPARENT;
        this.penColour = Color.BLACK;

        //Read.read(this);

        //redraw();

        //Canvas events
        addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(shapeType == ShapeType.PLOT){
                    Shape plot = new PaintPlot();
                    plot.addXCoord(event.getX());
                    plot.addYCoord(event.getY());
                    actions.add(plot);

                    plot.draw(gc);
                }
                else if(shapeType == ShapeType.POLYGON){
                    Shape polygon = new PaintPolygon();
                    polygon.addXCoord(event.getX());
                    polygon.addYCoord(event.getY());
                    actions.add(polygon);

                    polygon.draw(gc);
                }
            }
        });

        addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        double x = event.getX();
                        double y = event.getY();

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
                        double x = event.getX();
                        double y = event.getY();

                        if(shapeType == ShapeType.RECTANGLE || shapeType == ShapeType.ELLIPSE || shapeType == ShapeType.LINE){
                            redrawShape(x, y);
                        }

                        redraw();
                    }
                });

        addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        double x = event.getX();
                        double y = event.getY();
                        if(shapeType == ShapeType.RECTANGLE || shapeType == ShapeType.ELLIPSE){
                            finalShape(x, y);
                        }
                        undoStack.updateHistoryListView(actions);
                        redraw();
                    }
                });
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
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
        shape.draw(gc);
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

    public void redraw(){
        gc.clearRect(0,0, this.getWidth(), this.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, pixels, pixels);
        gc.setFill(Color.TRANSPARENT);
        gc.setStroke(Color.BLACK);

        for(int index = 0; index < actions.size(); index++){
            actions.get(index).draw(gc);
        }
    }
}
