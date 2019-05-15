package paint;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public class SetStroke extends Action {
    Color stroke_colour;

    public SetStroke(Color colour) {
        stroke_colour = colour;
    }

    public void draw(GraphicsContext gc) {
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