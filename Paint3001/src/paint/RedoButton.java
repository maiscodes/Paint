package paint;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * Extends Button to redo an action on the canvas upon click
 */
public class RedoButton extends Button {
    private ArrayList<Action> undoList = new ArrayList<>();

    /**
     * Given a canvas object the redo button makes the canvas
     * redo an action when clicked.
     * @param canvas PaintCanvas object who's action properties will
     *               be changed by the redo button
     */
    public RedoButton (PaintCanvas canvas) {
        super.setText("Redo");

        addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        canvas.redoAction();
                    }
                });
    }
}