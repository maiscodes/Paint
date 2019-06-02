package paint;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

/**
 * Extends Action to set the fill colour to the specified colour, and prints the instruction of that action
 */
public class SetFill extends Action {
    Color fill_colour;

    /**
     * Given a colour the fill colour property of
     * the SetFill action is set to the specified value.
     * @param colour colour object
     */
    public SetFill(Color colour) {
        fill_colour = colour;
    }

    /**
     * Given the graphics context and the size of the canvas
     * the setFill action will directly affect, the fill colour
     * property of the graphics context is set to the current value
     * of this object.
     * @param gc graphics context of canvas
     * @param px double representing canvas size in pixels
     */
    public void draw(GraphicsContext gc, double px) {
        gc.setFill(fill_colour);
    }

    /**
     * Prints the instruction of the Set Fill action.
     * @return string containing instructions with FILL key word
     * and the corresponding RGB colour value.
     */
    public String printInstruction(){
        String instruction;
        if (fill_colour.getOpacity() == 0) {
            instruction = "FILL OFF";
        }
        else {
            instruction = String.format("FILL #%02X%02X%02X",(int)( fill_colour.getRed() * 255 ),
                    (int)( fill_colour.getGreen() * 255 ),
                    (int)( fill_colour.getBlue() * 255 ) );

        }

        return instruction;

    }
}
