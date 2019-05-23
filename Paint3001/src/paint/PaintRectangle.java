package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintRectangle extends Shape {

    public PaintRectangle(){
        super(ShapeType.RECTANGLE);
    }

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

        //gc.fillRect(x, y, w, h); nope this line of code doesnt work if trans
        if (gc.getFill() == Color.TRANSPARENT) {
            if (x > 1) {
                x = 1;
            }
            if (y > 1) {
                y = 1;
            }
            if (x < 0) {
                x = 0;
            }
            if (y < 0) {
                y = 0;
            }
            gc.strokeRect(x * px, y * px, w * px, h * px);

        }
        else {
            gc.fillRect(x * px, y * px, w * px, h * px);
            gc.strokeRect(x * px, y * px, w * px, h * px);
        }
        //gc.strokeLine(x_coord.get(0), y_coord.get(0), x_coord.get(1), y_coord.get(1));
        System.out.println(printInstruction());
    };

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