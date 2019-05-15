package paint;

import java.util.ArrayList;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintLine extends Shape {

    public PaintLine(){
        super(ShapeType.LINE);
    }

    public void draw(GraphicsContext gc){
        //possibly omit the SetStroke when canvas class stores the pen and fill colours
        //gc.getStroke();
        gc.strokeLine(x_coord.get(0), y_coord.get(0), x_coord.get(1), y_coord.get(1));
    };

    public String printInstruction(){
        Double X1 = Double.valueOf(x_coord.get(0))/canvas_px;
        Double X2 = Double.valueOf(x_coord.get(1))/canvas_px;
        Double Y1 =  Double.valueOf(y_coord.get(0))/canvas_px;
        Double Y2 =  Double.valueOf(y_coord.get(1))/canvas_px;
        String instruction = String.format("LINE %.2f %.2f %.2f %.2f",X1, Y1, X2, Y2);
        System.out.println(instruction);
        return instruction;
    }
}
