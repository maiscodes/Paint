package paint;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

public class RedoButton extends Button {
    private ArrayList<Action> undoList = new ArrayList<>();

    public RedoButton (PaintCanvas canvas) {
        super.setText("Redo");


        addEventHandler(MouseEvent.MOUSE_RELEASED,
                new EventHandler<MouseEvent>() {


                    @Override
                    public void handle(MouseEvent event) {
                        canvas.redoAction();
                        /*
                        ArrayList<Action> actions = canvas.getActions();

                        if (undo_btn.getUndoList().size() > 0) {
                            ArrayList<Action> newActions = new ArrayList<Action>();
                            ArrayList<Action> newUndoList = new ArrayList<Action>();
                            //newActions = actions;

                            for (int a = 0; a < actions.size(); a++) {
                                newActions.add(actions.get(a));
                            }
                            newActions.add(undo_btn.getUndoList().get(undo_btn.getUndoList().size() - 1));

                            System.out.println(undoList);
                            canvas.updateActions(newActions);
                            canvas.redraw();
                            undo_stack.updateHistoryListView(newActions);

                            //update undoList
                            for (int u = 0; u < undo_btn.getUndoList().size() - 1; u++) {
                                newUndoList.add(undo_btn.getUndoList().get(u));
                            }
                            undo_btn.updateUndoList(newUndoList);
                        }
                        */

                    }
                });
    }
}