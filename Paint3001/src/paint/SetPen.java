package paint;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public class SetPen extends Action {
    Color stroke_colour;

    public SetPen(Color colour) {
        stroke_colour = colour;
    }

    public void draw(GraphicsContext gc, double px) {
        gc.setStroke(stroke_colour);
    }

    public String printInstruction() {
        String instruction = String.format("PEN #%02X%02X%02X",(int)( stroke_colour.getRed() * 255 ),
                (int)( stroke_colour.getGreen() * 255 ),
                (int)( stroke_colour.getBlue() * 255 ) );
        System.out.println(instruction);
        return instruction;
    }
}