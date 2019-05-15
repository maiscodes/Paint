package paint;

import javafx.event.ActionEvent;
import javafx.scene.control.ColorPicker;
import javafx.event.EventHandler;

public class FillColourPicker extends ColorPicker {

    public FillColourPicker (PaintCanvas canvas){
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                canvas.setFillColour(getValue());

                SetFill setFill = new SetFill(getValue());
                canvas.addToActions(setFill);

            }
        };

        this.setOnAction(event);

    }

}
