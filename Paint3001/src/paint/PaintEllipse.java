package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Extends Shape to draw an ellipse on to the graphics context of the canvas
 */
public class PaintEllipse extends Shape {
    public PaintEllipse(){
        super(ShapeType.ELLIPSE);
    }

    /**
     * Given the graphics context and size of the canvas,
     * an ellipse is drawn onto the graphics context.
     * @param gc graphics context of the canvas which the ellipse will be drawn on
     * @param px double representing the size of the canvas in pixels
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
            gc.strokeOval(x*px, y*px, w*px, h*px);

        }
        else {
            gc.fillOval(x*px, y*px, w*px, h*px);
            gc.strokeOval(x*px, y*px, w*px, h*px);
        }
    };

    /**
     * Prints the instruction using and x and y coordinate properties
     * of this shape.
     * @return string containing instructions with ELLIPSE key word
     * and two of the ellipse's coordinates from top-left to bottom-right.
     */
    public String printInstruction(){
        Double X1 = Double.valueOf(x_coord.get(0));
        Double X2 = Double.valueOf(x_coord.get(1));
        Double Y1 =  Double.valueOf(y_coord.get(0));
        Double Y2 =  Double.valueOf(y_coord.get(1));
        String instruction = String.format("ELLIPSE %.2f %.2f %.2f %.2f",X1, Y1, X2, Y2);
        System.out.println(instruction);
        return instruction;
    }
}
