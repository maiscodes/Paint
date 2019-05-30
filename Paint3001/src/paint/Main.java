package paint;

import javafx.application.Application;

import javafx.stage.Stage;

/**
 * Starts the program and launches the GUI window
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        GUI window = new GUI();
    }


    public static void main(String args[]) {
        launch(args);
    }
}
