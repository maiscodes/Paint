package paint;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintLine extends Shape {

    public PaintLine(){
        super(ShapeType.LINE);
    }

    public void draw(GraphicsContext gc, double px){
        double x1, x2, y1, y2;
        x1 = x_coord.get(0);
        x2 = x_coord.get(1);
        y1 = y_coord.get(0);
        y2 = y_coord.get(1);

        //possibly omit the SetStroke when canvas class stores the pen and fill colours
        //gc.getStroke();
        gc.strokeLine(x1 * px, y1 * px, x2 * px, y2 *px);
    }

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
