package paint;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ColorPicker;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;

import java.util.ArrayList;
//import javafx.scene.shape.Line;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.shape.Ellipse;
//import javafx.scene.shape.Polygon;



public class Main extends Application {
    @Override
    public void start(Stage stage) {
        //compartmentalise later
        StackPane rootPane = new StackPane();
        //Instantiating the main class
        BorderPane window_container = new BorderPane();

        // create menu pane
        FlowPane menu_container = new FlowPane();

        final FileChooser fileChooser = new FileChooser();

        // creating the list view for the undo stack, maybe store shapes?
        VBox undo_container = new VBox();
        Label undo_lbl = new Label("Undo History");
        UndoHistoryListView<String> undo_stack = new UndoHistoryListView<String>();
        ObservableList<String> action_list = FXCollections.observableArrayList("Instruction 1", "Instruction 2", "Instruction 3");
        undo_stack.setItems(action_list);
        ObservableList undo_container_contents = undo_container.getChildren();
        //undo_container_contents.addAll(undo_lbl, undo_stack);

        //additional method to set style son!
        String style = "-fx-background-color: rgba(47, 51, 58, 0.9);";
        menu_container.setStyle(style);
        rootPane.getChildren().addAll(window_container, menu_container);
        Button open_btn = new Button("Open");
        Button save_btn = new Button("Save");
        Button new_btn = new Button("New");
        Label menu_lbl = new Label("Menu");
        ObservableList menuButtons = menu_container.getChildren();
        menuButtons.addAll(menu_lbl, open_btn, save_btn, new_btn);

        // create canvas, coordinates works from top left
        PaintCanvas canvas = new PaintCanvas(500, undo_stack);
        PaintCanvas tempCanvas = new PaintCanvas(500, undo_stack);
        Pane canvasPane = new Pane();

        //** RESIZABLE
        canvas.widthProperty().bind(canvasPane.widthProperty());
        canvas.heightProperty().bind(canvasPane.heightProperty());

        // ***
        tempCanvas.widthProperty().bind(canvasPane.widthProperty());
        tempCanvas.heightProperty().bind(canvasPane.heightProperty());

        //**
        canvasPane.getChildren().add(canvas);
        canvasPane.getChildren().add(tempCanvas);
        canvas.toFront();

        //Undo button here
        ViewActionsButton view_btn = new ViewActionsButton(canvas, tempCanvas, undo_stack);
        //** MOVED THE UNDO CONTAINER DOWN

        //ADDING THE UNDO STUFF HERE
        UndoButton undo_btn = new UndoButton(canvas, undo_stack);
        RedoButton redo_btn = new RedoButton(canvas, undo_stack, undo_btn);
        FlowPane undo_btn_container = new FlowPane();
        ObservableList undo_btn_contents = undo_btn_container.getChildren();
        undo_btn_contents.addAll(view_btn, undo_btn, redo_btn);

        undo_container_contents.addAll(undo_lbl, undo_stack, undo_btn_container);




        //END UNDO STUFF

        open_btn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("VEC files", "*.vec"));
                        File file = fileChooser.showOpenDialog(stage);
                        try {
                            if (file != null) {
                                Read.read(canvas, file);
                                canvas.redraw();
                            }
                        } catch (Exception err) {
                            err.printStackTrace();
                        }
                    }
                });



        save_btn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Save Drawing");
                        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("VEC files (*.vec)", "*.vec");
                        fileChooser.getExtensionFilters().add(extFilter);

                        File file = fileChooser.showSaveDialog(stage);
                        if (file != null) {
                            Write.write(canvas, file);
                        }
                    }
                });

        stage.addEventHandler(KeyEvent.KEY_PRESSED,
                new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent t) {
                        if(t.getCode()== KeyCode.ESCAPE){
                            canvas.completePolygon();
                            System.out.println("Poly done!");
                        }
                    }
                });

        //create tool buttons pane
        HBox drawingtools_container = new HBox();

        // create the buttons
        Button line_btn = new PaintButton(ShapeType.LINE, canvas);
        Button plot_btn = new PaintButton(ShapeType.PLOT, canvas);
        Button rect_btn = new PaintButton(ShapeType.RECTANGLE, canvas);
        Button ellipse_btn = new PaintButton(ShapeType.ELLIPSE, canvas);
        Button polygon_btn = new PaintButton(ShapeType.POLYGON, canvas);

        // add the buttons in two columns
        VBox toolbar_c1 = new VBox();
        VBox toolbar_c2 = new VBox();

        ObservableList c1_tools = toolbar_c1.getChildren();
        c1_tools.addAll(line_btn, plot_btn, rect_btn);

        ObservableList c2_tools = toolbar_c2.getChildren();
        c2_tools.addAll(ellipse_btn, polygon_btn);

        ObservableList toolbar_btns = drawingtools_container.getChildren();
        toolbar_btns.addAll(toolbar_c1, toolbar_c2);

        // create the colour tools container, can create function to return based on name
        VBox pencolour_container = new VBox();
        Label pencolour_lbl = new Label("Pen Colour");
        ColorPicker pencolour_picker = new PenColourPicker(canvas, undo_stack);

        // add the pen colour components to the container
        ObservableList pencolours_list = pencolour_container.getChildren();
        pencolours_list.addAll(pencolour_lbl, pencolour_picker);

        VBox shapefill_container = new VBox();
        Label shapefill_lbl = new Label("Shape Fill Colour");
        ColorPicker shapefill_picker = new FillColourPicker(canvas, undo_stack);
        ObservableList shapefill_list = shapefill_container.getChildren();
        shapefill_list.addAll(shapefill_lbl, shapefill_picker);



        // now put all the tools together
        Label drawingtools_lbl = new Label("Drawing Tools");
        VBox toolbar = new VBox();
        //looks like you can give elements ids and style using css file
        style = "-fx-background-color: rgba(79, 84, 91, 1);";
        toolbar.setStyle(style);
        ObservableList toolbar_contents = toolbar.getChildren();
        toolbar_contents.addAll(drawingtools_lbl, drawingtools_container, pencolour_container, shapefill_container, undo_container, view_btn);

        // also for functionality test click/hover event of canvas

        //idea for undo history stack -- get number value and then for loop recreate


        //Setting the top, bottom, center, right and left nodes to the pane

        window_container.setTop(menu_container);
        //window_container.setBottom(new TextField("Tools"));
        window_container.setLeft(toolbar);
        window_container.setRight(new TextField("Zoom"));
        window_container.setCenter(canvasPane);

        //Creating a scene object
        Scene scene = new Scene(rootPane);

        //Setting title to the Stage
        stage.setTitle("Paint3000");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }


    public static void main(String args[]) {
        launch(args);
    }
}
