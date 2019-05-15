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

    public PaintCanvas(int pixels) {
        this.pixels = pixels;
        super.setWidth(pixels);
        super.setHeight(pixels);
        this.gc = this.getGraphicsContext2D();
        this.shapeType = ShapeType.RECTANGLE;

        //Canvas events
        addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("X: " + event.getX() + "\nY: " + event.getY());
                        if(shapeType == ShapeType.PLOT){
                            Shape plot = new PaintPlot();
                            plot.addXCoord(event.getX());
                            plot.addYCoord(event.getY());

                            actions.add(plot);

                            plot.draw(gc);
                        }
                        if(shapeType == ShapeType.RECTANGLE){
                            Shape rect = new PaintRectangle();
                            rect.addXCoord(event.getX());
                            rect.addYCoord(event.getY());
                            rect.addXCoord(event.getX());
                            rect.addYCoord(event.getY());

                            actions.add(rect);
                        }
                    }
                });

        addEventHandler(MouseEvent.MOUSE_DRAGGED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("X: " + event.getX() + "\nY: " + event.getY());
                        if(shapeType == ShapeType.RECTANGLE){
                            Shape rect = (Shape) actions.get(actions.size() - 1);

                            rect.setX2Coord(event.getX());
                            rect.setY2Coord(event.getY());

                            rect.draw(gc);
                        }
                        redraw();
                    }
                });

        addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        System.out.println("X: " + event.getX() + "\nY: " + event.getY());
                        if(shapeType == ShapeType.RECTANGLE){
                            Shape rect = (Shape) actions.get(actions.size() - 1);

                            rect.setX2Coord(event.getX());
                            rect.setY2Coord(event.getY());

                            actions.set(actions.size() - 1, rect);
                        }
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

    private void redraw(){
        gc.clearRect(0,0, this.getWidth(), this.getHeight());
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 400, 400);

        gc.setFill(Color.GREEN);

        for(int index = 0; index < actions.size(); index++){
            actions.get(index).draw(gc);
        }
    }
}

