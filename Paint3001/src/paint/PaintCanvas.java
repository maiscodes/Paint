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

    public PaintCanvas(int pixels) {
        this.pixels = pixels;
        super.setWidth(pixels);
        super.setHeight(pixels);
        this.fillColour = Color.TRANSPARENT;
        this.penColour = Color.BLACK;
        this.gc = this.getGraphicsContext2D();
        this.shapeType = ShapeType.PLOT;

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

                        if(shapeType == ShapeType.ELLIPSE){
                            Shape ellipse = new PaintEllipse();
                            //could be set X1, set X2, then for polygon special case
                            ellipse.addXCoord(event.getX());
                            ellipse.addYCoord(event.getY());
                            ellipse.addXCoord(event.getX());
                            ellipse.addYCoord(event.getY());

                            actions.add(ellipse);
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

                        if(shapeType == ShapeType.ELLIPSE){
                            Shape ellipse = (Shape) actions.get(actions.size() - 1);

                            ellipse.setX2Coord(event.getX());
                            ellipse.setY2Coord(event.getY());

                            ellipse.draw(gc);
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

                            if (event.getX() < rect.getXCoords().get(0)) {
                                double tempX = rect.getXCoords().get(0);
                                rect.setX1Coord(event.getX());
                                rect.setX2Coord(tempX);
                            }

                            if (event.getY() < rect.getYCoords().get(0)) {
                                double tempY = rect.getYCoords().get(0);
                                rect.setY1Coord(event.getY());
                                rect.setY2Coord(tempY);
                            }


                            actions.set(actions.size() - 1, rect);
                            rect.printInstruction();
                        }

                        if(shapeType == ShapeType.ELLIPSE){
                            Shape ellipse = (Shape) actions.get(actions.size() - 1);

                            if (event.getX() < ellipse.getXCoords().get(0)) {
                                double tempX = ellipse.getXCoords().get(0);
                                ellipse.setX1Coord(event.getX());
                                ellipse.setX2Coord(tempX);
                            }

                            if (event.getY() < ellipse.getYCoords().get(0)) {
                                double tempY = ellipse.getYCoords().get(0);
                                ellipse.setY1Coord(event.getY());
                                ellipse.setY2Coord(tempY);
                            }


                            actions.set(actions.size() - 1, ellipse);

                        }
                        redraw();

                        //checking instructions are correct
                        for(int index = 0; index < actions.size(); index++){
                            actions.get(index).printInstruction();
                        }
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


    private void redraw(){
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

