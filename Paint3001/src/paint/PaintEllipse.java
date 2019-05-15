package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintEllipse extends Shape {
    public PaintEllipse(){
        super(ShapeType.ELLIPSE);
    }

    public void draw(GraphicsContext gc){
        double x = x_coord.get(0);
        double y = y_coord.get(0);
        double w = x_coord.get(1) - x; //width at center of the oval
        double h = y_coord.get(1) - y;
        //gc.fillRect(x, y, w, h); nope this line of code doesnt work if trans
        if (gc.getFill() == Color.TRANSPARENT) {
            gc.strokeOval(x, y, w, h);
        }
        else {
            gc.fillOval(x, y, w, h);
            gc.strokeOval(x, y, w, h);
        }
    };

    public String printInstruction(){
        Double X1 = Double.valueOf(x_coord.get(0))/canvas_px;
        Double X2 = Double.valueOf(x_coord.get(1))/canvas_px;
        Double Y1 =  Double.valueOf(y_coord.get(0))/canvas_px;
        Double Y2 =  Double.valueOf(y_coord.get(1))/canvas_px;
        String instruction = String.format("ELLIPSE %.2f %.2f %.2f %.2f",X1, Y1, X2, Y2);
        System.out.print(instruction);
        return instruction;
    }
}
