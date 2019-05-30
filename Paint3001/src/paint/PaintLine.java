package paint;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintLine extends Shape {

    public PaintLine(){
        super(ShapeType.LINE);
    }

    /**
     * Given the graphics context and size of the canvas,
     * a line is drawn onto the graphics context.
     * @param gc graphics context of the canvas which the line will be drawn on
     * @param px the size in pixels of the canvas
     */
    public void draw(GraphicsContext gc, double px){
        double x1, x2, y1, y2;
        x1 = x_coord.get(0);
        x2 = x_coord.get(1);
        y1 = y_coord.get(0);
        y2 = y_coord.get(1);

        gc.strokeLine(x1 * px, y1 * px, x2 * px, y2 *px);
    }

    /**
     * Prints the instruction using and x and y coordinate properties
     * of this shape.
     * @return string containing instructions with LINE key word
     * and two of the line's coordinates.
     */
    public String printInstruction(){
        Double X1 = Double.valueOf(x_coord.get(0));
        Double X2 = Double.valueOf(x_coord.get(1));
        Double Y1 =  Double.valueOf(y_coord.get(0));
        Double Y2 =  Double.valueOf(y_coord.get(1));
        String instruction = String.format("LINE %.2f %.2f %.2f %.2f",X1, Y1, X2, Y2);
        System.out.println(instruction);
        return instruction;
    }
}
