package paint;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class UndoButton extends Button {
    private ArrayList<Action> undoList = new ArrayList<>();

    /**
     * Given a canvas object the undo button is created with a
     * click event which causes the canvas to run its undoAction
     * method.
     * @param canvas PaintCanvas object who's actions will be undone
     */
    public UndoButton (PaintCanvas canvas) {
        super.setText("Undo");

        if (canvas.getActions().size() < 1) {
            setDisabled(true);
        }

        if (canvas.getActions().size() >= 1) {
            setDisabled(false);
        }

        addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        canvas.undoAction();


                    }
                });
    }

}

