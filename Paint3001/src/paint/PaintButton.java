package paint;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;


public class PaintButton extends Button {
    /**
     * Given a ShapeType enum and canvas object it creates a button
     * which the user will click on to choose the current shape type
     * they want to draw on the canvas.
     *
     * @param shapeType enumerator representing the specific drawing tool
     * @param canvas canvas which this particular shape will be drawn on
     */
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
