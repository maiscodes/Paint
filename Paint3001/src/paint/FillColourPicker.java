package paint;

import javafx.event.ActionEvent;
import javafx.scene.control.ColorPicker;
import javafx.event.EventHandler;

/**
 * Creates a colour picker that allows the user to select a fill colour
 */
public class FillColourPicker extends ColorPicker {


    /**
     * Given a canvas and list view object it creates a colour picker
     * which when changed updates the canvas and list view accordingly.
     * @param canvas the canvas which updates after the change
     * @param undoStack the list view which updates after the change
     */
    public FillColourPicker (PaintCanvas canvas, UndoHistoryListView<String> undoStack){
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                canvas.setFillColour(getValue());

                SetFill setFill = new SetFill(getValue());
                canvas.addToActions(setFill);
                undoStack.updateHistoryListView(canvas.getActions());

            }
        };

        this.setOnAction(event);

    }

}
