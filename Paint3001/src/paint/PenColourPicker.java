package paint;

import javafx.event.ActionEvent;
import javafx.scene.control.ColorPicker;
import javafx.event.EventHandler;

public class PenColourPicker extends ColorPicker {
    /**
     * Given a canvas and list view object it creates a colour picker
     * which when changed updates the canvas and list view accordingly.
     * @param canvas the canvas which updates after the change
     * @param undoStack the list view which updates after the change
     */
    public PenColourPicker (PaintCanvas canvas, UndoHistoryListView<String> undoStack){
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                canvas.setPenColour(getValue());

                SetPen setPen = new SetPen(getValue());
                canvas.addToActions(setPen);
                undoStack.updateHistoryListView(canvas.getActions());


            }
        };

        this.setOnAction(event);

    }

}
