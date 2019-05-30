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


    /**
     * Constructs new Shape with given ShapeType
     *
     * @param shape_type ShapeType
     */
    public Shape(ShapeType shape_type){
        //do nothing for now
        //this.pen_colour = pen_colour;
        //this.fill_colour = fill_colour;
        this.shape_type = shape_type;
        x_coord = new ArrayList<>();
        y_coord = new ArrayList<>();
    }

    /**
     * Returns ArrayList of X-coordinates of the shape in vector format
     *
     * @return ArrayList<Double>
     */
    public ArrayList<Double> getXCoords(){
        return x_coord;
    }

    /**
     * Returns ArrayList of Y-coordinates of the shape in vector format
     *
     * @return ArrayList<Double>
     */
    public ArrayList<Double> getYCoords(){
        return y_coord;
    }

    /**
     * Adds given x value to X-coordinates of the shape
     *
     * @param x Vector X-coordinate
     */
    public void addXCoord(double x){
        x = checkXY(x);
        x_coord.add(x);
    }

    /**
     * Adds given x value to Y-coordinates of the shape
     *
     * @param y Vector Y-coordinate
     */
    public void addYCoord(double y){
        y = checkXY(y);
        y_coord.add(y);

    }

    /**
     * Changes the first X-coordinate of the shape
     *
     * @param x Vector X-coordinate
     */
    public void setX1Coord(double x){
        x = checkXY(x);
        x_coord.set(0, x);
    }

    /**
     * Changes the first Y-coordinate of the shape
     *
     * @param y Vector Y-coordinate
     */
    public void setY1Coord(double y){
        y = checkXY(y);
        y_coord.set(0, y);
    }

    /**
     * Changes the second X-coordinate of the shape
     *
     * @param x Vector X-coordinate
     */
    public void setX2Coord(double x){
        x = checkXY(x);
        x_coord.set(1, x);
    }

    /**
     * Changes the second Y-coordinate of the shape
     *
     * @param y Vector Y-coordinate
     */
    public void setY2Coord(double y){
        y = checkXY(y);
        y_coord.set(1, y);
    }

    /**
     * Checks whether vector coordinate c is within vector format
     * and changes to absolute minimum or maximum vector coordinate value
     *
     * @param c Vector coordinate
     * @return Corrected or original vector value
     */
    double checkXY(double c) {
        if (c > 1) {
            return 1;
        }
        if (c < 0) {
            return 0;
        }
        return c;
    }

    /**
     * Returns ShapeType of the shape
     *
     * @return ShapeType
     */
    public ShapeType getShapeType(){
        return this.shape_type;
    }

    //public abstract void draw(GraphicsContext gc);

    //public abstract String printInstruction();

}
