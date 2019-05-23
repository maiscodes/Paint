package paint;
import javafx.scene.canvas.Canvas;
import org.junit.jupiter.api.*;
import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReadTest {
    PaintCanvas canvas;
    UndoHistoryListView<String> undo_stack;
    String testVEC = "RECTANGLE 0.02 0.02 0.08 0.08RECTANGLE 0.03 0.03 0.09 0.09FILL #FFFFFFRECTANGLE 0.12 0.02 0.18 0.08RECTANGLE 0.13 0.03 0.19 0.09";

    @BeforeEach
    public void setUpFile(){
        canvas = new PaintCanvas(100, undo_stack);
    }

    @Test
    public void readVECFile(){
        File file = new File("files/test.vec");
        try {
            Read.read(canvas, file);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        ArrayList<Action> actions = canvas.getActions();

        String check = "";

        for (Action a:actions
             ) {
            check += a.printInstruction();
        }

        assertEquals(testVEC, check);
    }

    @Test
    public void readELSEFile(){
        assertThrows(vecExceptions.class, () -> {
            File file = new File("files/test.txt");
            Read.read(canvas, file);
        });
    }
}