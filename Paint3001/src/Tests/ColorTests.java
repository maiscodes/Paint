package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import paint.*;
import javafx.scene.paint.Color;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ColorTests {
    Action paint;
    int RGB[] = {10, 50, 60};

    @BeforeEach
    public void setUp(){
        paint = null;
    }

    @Test
    public void testFillColor(){
        Color colour = Color.rgb(RGB[0], RGB[1], RGB[2]);
        paint = new SetFill(colour);

        String actual = paint.printInstruction();
        String expected = String.format("FILL #%02X%02X%02X", RGB[0], RGB[1], RGB[2]);

        assertEquals(expected, actual);
    }

    @Test
    public void testFillOff(){
        Color colour = Color.rgb(RGB[0], RGB[1], RGB[2], 0);
        paint = new SetFill(colour);

        String actual = paint.printInstruction();
        String expected = "FILL OFF";

        assertEquals(expected, actual);
    }

    @Test
    public void testPenColor(){
        Color colour = Color.rgb(RGB[0], RGB[1], RGB[2]);
        paint = new SetPen(colour);

        String actual = paint.printInstruction();
        String expected = String.format("PEN #%02X%02X%02X", RGB[0], RGB[1], RGB[2]);

        assertEquals(expected, actual);
    }
}