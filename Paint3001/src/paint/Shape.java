package paint;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape extends Action{
    protected ShapeType shape_type;
    //protected Color pen_colour;
    //protected Color fill_colour;
    protected ArrayList<Double> x_coord;
    protected ArrayList<Double> y_coord;
    //for now, have canvas px stored-  later when canvas class can get from there


    public Shape(ShapeType shape_type){
        //do nothing for now
        //this.pen_colour = pen_colour;
        //this.fill_colour = fill_colour;
        this.shape_type = shape_type;
        x_coord = new ArrayList<>();
        y_coord = new ArrayList<>();
    }

    // instead of get, have print the instructions out, abstract?
    public ArrayList<Double> getXCoords(){
        return x_coord;
    }

    public ArrayList<Double> getYCoords(){
        return y_coord;
    }

    public void addXCoord(double x){
        x = checkXY(x);
        x_coord.add(x);
    }

    public void addYCoord(double y){
        y = checkXY(y);
        y_coord.add(y);

    }

    public void setX1Coord(double x){
        x = checkXY(x);
        x_coord.set(0, x);
    }

    public void setY1Coord(double y){
        y = checkXY(y);
        y_coord.set(0, y);
    }

    public void setX2Coord(double x){
        x = checkXY(x);
        x_coord.set(1, x);
    }

    public void setY2Coord(double y){
        y = checkXY(y);
        y_coord.set(1, y);
    }

    public double checkXY(double c) {
        if (c > 1) {
            return 1;
        }
        if (c < 0) {
            return 0;
        }
        return c;
    }
    public ShapeType getShapeType(){
        return this.shape_type;
    }

    //public abstract void draw(GraphicsContext gc);

    //public abstract String printInstruction();

}
