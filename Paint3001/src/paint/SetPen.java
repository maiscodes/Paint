package paint;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public class SetPen extends Action {
    Color stroke_colour;

    /**
     * Given a colour the pen colour property of
     * the SetPen action is set to the specified value.
     * @param colour colour object
     */
    public SetPen(Color colour) {
        stroke_colour = colour;
    }

    /**
     * Given the graphics context and the size of the canvas
     * the SetPen action will directly affect, the stroke colour
     * property of the graphics context is set to the current value
     * of this object.
     * @param gc graphics context of canvas
     * @param px double representing canvas size in pixels
     */
    public void draw(GraphicsContext gc, double px) {
        gc.setStroke(stroke_colour);
    }

    /**
     * Prints the instruction of the Set Pen action.
     * @return string containing instructions with PEN key word
     * and the corresponding RGB colour value.
     */
    public String printInstruction() {
        String instruction = String.format("PEN #%02X%02X%02X",(int)( stroke_colour.getRed() * 255 ),
                (int)( stroke_colour.getGreen() * 255 ),
                (int)( stroke_colour.getBlue() * 255 ) );
        System.out.println(instruction);
        return instruction;
    }
}