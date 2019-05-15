package paint;
import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

public class SetFill extends Action {
    Color fill_colour;

    public SetFill(Color colour) {
        fill_colour = colour;
    }

    public void draw(GraphicsContext gc) {
        gc.setFill(fill_colour);
    }

    public String printInstruction(){
        String instruction;
        if (fill_colour == Color.TRANSPARENT) {
            instruction = "FILL OFF";
        }
        else {
            instruction = String.format("FILL #%02X%02X%02X",(int)( fill_colour.getRed() * 255 ),
                    (int)( fill_colour.getGreen() * 255 ),
                    (int)( fill_colour.getBlue() * 255 ) );

        }
        System.out.println(instruction);

        return instruction;

    }
}
