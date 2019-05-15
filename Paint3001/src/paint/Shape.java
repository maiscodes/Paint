package paint;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class Shape extends Action{
    protected ShapeType shape_type;
    //protected Color pen_colour;
    //protected Color fill_colour;
    protected ArrayList<Integer> x_coord;
    protected ArrayList<Integer> y_coord;
    //for now, have canvas px stored-  later when canvas class can get from there
    protected Integer canvas_px = 400;


    public Shape(ShapeType shape_type){
        //do nothing for now
        //this.pen_colour = pen_colour;
        //this.fill_colour = fill_colour;
        this.shape_type = shape_type;
        x_coord = new ArrayList<>();
        y_coord = new ArrayList<>();
    }

    // instead of get, have print the instructions out, abstract?
    public ArrayList<Integer> getXCoords(){
        return x_coord;
    }

    public ArrayList<Integer> getYCoords(){
        return y_coord;
    }

    public void addXCoord(Integer x){
        x_coord.add(x);
    }

    public void addYCoord(Integer y){
        y_coord.add(y);
    }

    //public abstract void draw(GraphicsContext gc);

    //public abstract String printInstruction();

}
