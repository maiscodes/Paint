package paint;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

// could be further divided
public class PaintButton extends Button {
    public PaintButton(ShapeType shapeType, PaintCanvas canvas) {
        super.setText(shapeType.toString());
        addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                            canvas.setShapeType(shapeType);
                        }
                    });

    }
}
