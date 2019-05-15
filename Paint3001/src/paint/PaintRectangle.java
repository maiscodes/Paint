package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintRectangle extends Shape {

    public PaintRectangle(){
        super(ShapeType.RECTANGLE);
    }

    public void draw(GraphicsContext gc){
        Integer x = x_coord.get(0);
        Integer y = y_coord.get(0);
        Integer w = x_coord.get(1) - x;
        Integer h = y_coord.get(1) - y;
        //gc.fillRect(x, y, w, h); nope this line of code doesnt work if trans
        if (gc.getFill() == Color.TRANSPARENT) {
            gc.strokeRect(x, y, w, h);
        }
        else {
            gc.fillRect(x, y, w, h);
            gc.strokeRect(x, y, w, h);
        }
        //gc.strokeLine(x_coord.get(0), y_coord.get(0), x_coord.get(1), y_coord.get(1));
    };

    public String printInstruction(){
        Double X1 = Double.valueOf(x_coord.get(0))/canvas_px;
        Double X2 = Double.valueOf(x_coord.get(1))/canvas_px;
        Double Y1 =  Double.valueOf(y_coord.get(0))/canvas_px;
        Double Y2 =  Double.valueOf(y_coord.get(1))/canvas_px;
        String instruction = String.format("RECTANGLE %.2f %.2f %.2f %.2f",X1, Y1, X2, Y2);
        System.out.println(instruction);
        return instruction;
    }
}