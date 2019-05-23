package paint;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class UndoButton extends Button {
    private ArrayList<Action> undoList = new ArrayList<>();

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
                        /*
                        ArrayList<Action> actions = canvas.getActions();

                        if (actions.size() > 0) {
                            undoList.add(actions.get(actions.size() - 1));
                            ArrayList<Action> newActions = new ArrayList<Action>();
                            for (int a = 0; a< actions.size()-1; a++) {
                                newActions.add(actions.get(a));
                            }

                            System.out.println(undoList);
                            canvas.updateActions(newActions);
                            canvas.redraw();
                            undo_stack.updateHistoryListView(newActions);
                        }

                        //check actions and enable/disable accordingly

                        */

                    }
                });
    }

    /*
    public ArrayList<Action> getUndoList(){
        return undoList;
    }

    public void updateUndoList(ArrayList<Action> newUndoList){
        undoList.clear();
        for (int a = 0; a < newUndoList.size(); a++) {
            undoList.add(newUndoList.get(a));
        }
    }
*/
}

