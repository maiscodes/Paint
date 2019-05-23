package paint;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WriteTest {
    PaintCanvas canvas;
    UndoHistoryListView<String> undo_stack;
    String fileName = "file";
    String testVEC = "RECTANGLE 0.02 0.02 0.08 0.08RECTANGLE 0.03 0.03 0.09 0.09FILL #FFFFFFRECTANGLE 0.12 0.02 0.18 0.08RECTANGLE 0.13 0.03 0.19 0.09";

    @BeforeEach
    public void setUp(){
        canvas = new PaintCanvas(500, undo_stack);
    }

    @Test
    public void checkWriteExtension(){
        Shape line = new PaintLine();
        line.addXCoord(10);
        line.addXCoord(20);
        line.addYCoord(10);
        line.addYCoord(20);
        canvas.addToActions(line);
        canvas.addToActions(line);

        Write.write(canvas, new File(fileName + ".VEC"));

        try{
            Read.read(canvas, new File(fileName + ".VEC"));
        }
        catch (Exception e){
            e.printStackTrace();
            assertEquals("File found", "File not found");
        }

        assertEquals("File found", "File found");
    }
    @Test
    public void checkWrongExtension(){
        Shape line = new PaintLine();
        line.addXCoord(10);
        line.addXCoord(20);
        line.addYCoord(10);
        line.addYCoord(20);
        canvas.addToActions(line);
        canvas.addToActions(line);

        Write.write(canvas, new File(fileName + ".txt"));

        try{
            Read.read(canvas, new File(fileName + ".txt" + ".VEC"));
        }
        catch (Exception e){
            e.printStackTrace();
            assertEquals("File found", "File not found");
        }

        assertEquals("File found", "File found");
    }

    @Test
    public void correctContent(){
        Shape line = new PaintLine();
        line.addXCoord(10);
        line.addXCoord(20);
        line.addYCoord(10);
        line.addYCoord(20);
        canvas.addToActions(line);
        canvas.addToActions(line);

        Write.write(canvas, new File(fileName + ".VEC"));
        canvas.clearActions();

        try{
            Read.read(canvas, new File(fileName + ".VEC"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        String check = "";
        ArrayList<Action> actions = canvas.getActions();
        for (Action a: actions
             ) {
            check += a.printInstruction();
        }

        assertEquals("LINE 0.02 0.02 0.04 0.04LINE 0.02 0.02 0.04 0.04", check);
    }

}