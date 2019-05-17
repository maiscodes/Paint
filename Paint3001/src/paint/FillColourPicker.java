package paint;

import javafx.event.ActionEvent;
import javafx.scene.control.ColorPicker;
import javafx.event.EventHandler;

public class FillColourPicker extends ColorPicker {

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
