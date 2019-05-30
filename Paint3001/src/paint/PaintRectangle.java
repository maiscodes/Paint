package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Extends Shape to draw a rectangle on to the graphics context of the canvas
 */
public class PaintRectangle extends Shape {

    public PaintRectangle(){
        super(ShapeType.RECTANGLE);
    }

    /**
     * Given the graphics context and size of the canvas,
     *  a rectangle is drawn onto the graphics context.
     * @param gc graphics context of the canvas which the rectangle will be drawn on
     * @param px the size in pixels of the canvas
     */
    public void draw(GraphicsContext gc, double px){
        double x, y, w, h;

        if(x_coord.get(1) < x_coord.get(0)){
            x = x_coord.get(1);
            w = Math.abs(x_coord.get(0) - x);
        }
        else{
            x = x_coord.get(0);
            w = Math.abs(x_coord.get(1) - x);
        }
        if(y_coord.get(1) < y_coord.get(0)){
            y = y_coord.get(1);
            h = Math.abs(y_coord.get(0) - y);
        }
        else{
            y = y_coord.get(0);
            h = Math.abs(y_coord.get(1) - y);
        }

        if (gc.getFill() == Color.TRANSPARENT) {
            gc.strokeRect(x * px, y * px, w * px, h * px);

        }
        else {
            gc.fillRect(x * px, y * px, w * px, h * px);
            gc.strokeRect(x * px, y * px, w * px, h * px);
        }

        System.out.println(printInstruction());
    };

    /**
     * Prints the instruction using and x and y coordinate properties
     * of this shape.
     * @return string containing instructions with RECTANGLE key word
     * and two of the rectangle's coordinates from top-left to bottom-right.
     */
    public String printInstruction(){
        Double X1 = Double.valueOf(x_coord.get(0));
        Double X2 = Double.valueOf(x_coord.get(1));
        Double Y1 =  Double.valueOf(y_coord.get(0));
        Double Y2 =  Double.valueOf(y_coord.get(1));
        String instruction = String.format("RECTANGLE %.2f %.2f %.2f %.2f",X1, Y1, X2, Y2);
        System.out.println(instruction);
        return instruction;
    }
}