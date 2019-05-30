package paint;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import java.util.ArrayList;

public class ViewActionsButton extends Button {
    /**
     * Given the master canvas, temporary canvas and undo history list
     * view GUI components, the view actions buttons looks at
     * the current item selected in the undo history list
     * and renders the master canvas' actions up to that point
     * on a temporary canvas.
     * @param canvas PaintCanvas object representing the master canvas
     * @param tempCanvas PaintCanvas object representing a temporary canvas which will be
     *                   drawn on top of the master canvas
     * @param undo_stack UndoHistoryListView(String) representing the undo stack which the selection
     *                   will be made from
     */
    public ViewActionsButton(PaintCanvas canvas, PaintCanvas tempCanvas, UndoHistoryListView<String> undo_stack) {
        super.setText("View");
        addEventHandler(MouseEvent.MOUSE_PRESSED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        System.out.print("Undo Button Pressed");
                        int actionIndex = undo_stack.getSelectedAction();
                        System.out.print(actionIndex);
                        ArrayList<Action> actions = canvas.getActions();
                        ArrayList<Action> newActions = new ArrayList<Action>();

                        for (int a = 0; a< actionIndex; a++) {
                            newActions.add(actions.get(a));
                        }

                        tempCanvas.updateActions(newActions);
                        tempCanvas.redraw();
                        tempCanvas.toFront();



                    }
                });

        addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {

                    @Override
                    public void handle(MouseEvent event) {
                        canvas.toFront();

                    }
                });
    }
}
