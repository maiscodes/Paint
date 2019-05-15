package paint;

import javafx.event.ActionEvent;
import javafx.scene.control.ColorPicker;
import javafx.event.EventHandler;

public class PenColourPicker extends ColorPicker {

    public PenColourPicker (PaintCanvas canvas){
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                canvas.setPenColour(getValue());

                SetPen setPen = new SetPen(getValue());
                canvas.addToActions(setPen);

            }
        };

        this.setOnAction(event);

    }

}
