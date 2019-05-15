package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.image.PixelWriter;



public class PaintPlot extends Shape {

    public PaintPlot(){
        super(ShapeType.PLOT);
    }

    public void draw(GraphicsContext gc){
        //possibly omit the SetStroke when canvas class stores the pen and fill colours
        //gc.getStroke();

        //draw square dot
        PixelWriter pixel_writer = gc.getPixelWriter();
        pixel_writer.setColor(x_coord.get(0), y_coord.get(0), (Color)gc.getStroke());

    };

    public String printInstruction(){
        Double x = Double.valueOf(x_coord.get(0))/canvas_px;
        Double y =  Double.valueOf(y_coord.get(0))/canvas_px;
        String instruction = String.format("PLOT %.2f %.2f",x ,y );
        System.out.println(instruction);
        return instruction;
    }
}
