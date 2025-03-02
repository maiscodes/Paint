package paint;

import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;

/**
 * Extends ListView to update the view with specified actions and gets the index of that action
 * @param <String> String
 */
public class UndoHistoryListView<String> extends ListView {
    public UndoHistoryListView() {
    }

    /**
     * Given a list of actions the history list view will update
     * its values with the specified actions.
     * @param actions ArrayList(Action) representing the new actions
     */
    public void updateHistoryListView(ArrayList<Action> actions){
        this.getItems().clear();
        for (int a = 0; a < actions.size(); a++) {
            this.getItems().add(actions.get(a).printInstruction());
        }
    }

    /**
     * Returns the index of the selected action
     * @return integer representing the selected action
     */
    public int getSelectedAction(){
        return this.getSelectionModel().getSelectedIndex();

    }

}
