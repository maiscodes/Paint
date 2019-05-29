package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Color;
import javafx.scene.image.PixelWriter;



public class PaintPlot extends Shape {

    public PaintPlot(){
        super(ShapeType.PLOT);
    }

    /**
     * Given the graphics context and size of the canvas,
     * a dot is drawn onto the graphics context.
     * @param gc graphics context of the canvas which the dot will be drawn on
     * @param px the size in pixels of the canvas
     */
    public void draw(GraphicsContext gc, double px){
        PixelWriter pixel_writer = gc.getPixelWriter();
        pixel_writer.setColor((int) Math.round(x_coord.get(0) * px), (int) Math.round(y_coord.get(0) * px), (Color)gc.getStroke());

    };

    /**
     * Prints the instruction using and x and y coordinate properties
     * of this shape.
     * @return string containing instructions with PLOT key word
     * and the plot's x and y coordinates.
     */
    public String printInstruction(){
        Double x = Double.valueOf(x_coord.get(0));
        Double y =  Double.valueOf(y_coord.get(0));
        String instruction = String.format("PLOT %.2f %.2f", x, y);
        System.out.println(instruction);
        return instruction;
    }
}
