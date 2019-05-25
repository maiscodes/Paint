package paint;
import javafx.scene.canvas.Canvas;
import org.junit.jupiter.api.*;
import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ReadTest {
    PaintCanvas canvas;
    UndoHistoryListView<String> undo_stack;
    String testVEC = "RECTANGLE 0.10 0.10 0.40 0.40RECTANGLE 0.15 0.15 0.45 0.45FILL #FFFFFFRECTANGLE 0.60 0.10 0.90 0.40RECTANGLE 0.65 0.15 0.95 0.45";
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