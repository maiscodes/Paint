package paint;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.InputStream;
//import javafx.scene.shape.Line;
//import javafx.scene.shape.Rectangle;
//import javafx.scene.shape.Ellipse;
//import javafx.scene.shape.Polygon;



public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception{
        //compartmentalise later
        StackPane rootPane = new StackPane();
        String base_style = "-fx-background-color: #02031E;";
        rootPane.setStyle(base_style);
        //Instantiating the main class
        BorderPane window_container = new BorderPane();
        window_container.setId("background");

        // create menu pane
        //FlowPane menu_container = new FlowPane();
        ToolBar toolBar = new ToolBar();
        window_container.setTop(toolBar);

        Region spacer = new Region();
        spacer.getStyleClass().setAll("spacer");

        HBox buttonBar = new HBox();
        buttonBar.getStyleClass().setAll("segmented-button-bar");
        Button open_btn = new Button("Open");
        Button save_btn = new Button("Save");
        Button new_btn = new Button("New");
        Button view_btn = new Button("View");
        open_btn.getStyleClass().addAll("first");
        view_btn.getStyleClass().addAll("last", "capsule");
        buttonBar.getChildren().addAll(open_btn, save_btn, new_btn, view_btn);
        toolBar.getItems().addAll(spacer, buttonBar);

        String style="-fx-base: rgb(39, 40, 40); -fx-font-size: 12pt; -fx-background-color: linear-gradient(to bottom, derive(rgb(39, 40, 40),-30%), derive(rgb(39, 40, 40),-60%)),        linear-gradient(to bottom, rgb(74, 75, 78)2%, rgb(39, 40, 40) 98%); -fx-background-insets: 0, 0 0 1 0; -fx-padding: .9em 0.416667em .9em 0.416667em; -fx-effect: dropshadow(two-pass-box,black,5,.2,0,0);";
        toolBar.setStyle(style);
        String button_style= "-fx-background-color:rgb(5, 5, 5), rgb(87, 89, 92), linear-gradient(to bottom, rgb(74, 75, 78) 2%, rgb(39, 40, 40) 98%); -fx-background-insets: 0, 1 1 1 0, 2 1 1 1; -fx-background-radius: 0; -fx-padding: 0.4em 1.833333em 0.4em 1.833333em;";
        open_btn.setStyle(button_style);

        save_btn.setStyle(button_style);
        new_btn.setStyle(button_style);
        view_btn.setStyle(button_style);

        final FileChooser fileChooser = new FileChooser();

        //rootPane.getChildren().addAll(window_container, menu_container);

        //Label menu_lbl = new Label("Menu");
       // ObservableList menuButtons = menu_container.getChildren();
       // menuButtons.addAll(menu_lbl, open_btn, save_btn, new_btn);

        String button_pressed_Style = "-fx-background-color: rgb(5, 5, 5), rgb(55, 57, 58), linear-gradient(to top, rgb(74, 75, 78) 2%, rgb(39, 40, 40) 98%);-fx-background-insets: 0, 1 1 1 0, 2 1 1 1;\n" +
                "    -fx-background-radius: 0;\n" +
                "    -fx-padding: 0.4em 1.833333em 0.4em 1.833333em";
        // create canvas, coordinates works from top left
       PaintCanvas canvas = new PaintCanvas(500);

        open_btn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        open_btn.setStyle(button_pressed_Style);
                        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("VEC files", "*.vec"));
                        File file = fileChooser.showOpenDialog(stage);
                        open_btn.setStyle(button_style);
                        if (file != null) {
                            Read.read(canvas, file);
                            canvas.redraw();
                        }
                    }
                });

        save_btn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        save_btn.setStyle(button_pressed_Style);
                        FileChooser fileChooser = new FileChooser();
                        fileChooser.setTitle("Save Drawing");
                        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("VEC files (*.vec)", "*.vec");
                        fileChooser.getExtensionFilters().add(extFilter);

                        File file = fileChooser.showSaveDialog(stage);
                        save_btn.setStyle(button_style);

                        if (file != null) {
                            Write.write(canvas, file);
                        }
                    }
                });

        //create tool buttons pane

        ToolBar drawingTools = new ToolBar();
        window_container.setBottom(drawingTools);

        Region spacer1 = new Region();
        spacer.getStyleClass().setAll("spacer1");

        HBox buttonBar2 = new HBox();
        buttonBar2.getStyleClass().setAll("segmented-button-bar-2");
        // create the buttons

        Button line_btn = new PaintButton(ShapeType.LINE, canvas);
        InputStream input = getClass().getResourceAsStream("line.png");
        Image image = new Image(input, 40, 40, true, true);
        ImageView imageView = new ImageView(image);
        line_btn.setGraphic(imageView);

        Button plot_btn = new PaintButton(ShapeType.PLOT, canvas);
        InputStream input2 = getClass().getResourceAsStream("dot2.jpg");
        Image image2 = new Image(input2, 40, 40, true, true);
        ImageView imageView2 = new ImageView(image2);
        plot_btn.setGraphic(imageView2);

        Button rect_btn = new PaintButton(ShapeType.RECTANGLE, canvas);
        InputStream input3 = getClass().getResourceAsStream("square2.png");
        Image image3 = new Image(input3, 58, 58, true, true);
        ImageView imageView3 = new ImageView(image3);
        rect_btn.setGraphic(imageView3);

        Button ellipse_btn = new PaintButton(ShapeType.ELLIPSE, canvas);
        InputStream input4 = getClass().getResourceAsStream("oval.png");
        Image image4 = new Image(input4, 50, 50, true, true);
        ImageView imageView4 = new ImageView(image4);
        ellipse_btn.setGraphic(imageView4);

        Button polygon_btn = new PaintButton(ShapeType.POLYGON, canvas);
        InputStream input5 = getClass().getResourceAsStream("hexy.png");
        Image image5 = new Image(input5, 40, 40, true, true);
        ImageView imageView5 = new ImageView(image5);
        polygon_btn.setGraphic(imageView5);

        buttonBar2.getChildren().addAll(line_btn, plot_btn, rect_btn, ellipse_btn, polygon_btn);
        drawingTools.getItems().addAll(spacer1, buttonBar2);

        String style0="-fx-base: rgb(39, 40, 40); -fx-font-size: 12pt; -fx-background-color:  #04052E; -fx-background-insets: 0, 0 0 1 0; -fx-padding: .9em 0.416667em .9em 0.416667em; -fx-effect: dropshadow(two-pass-box,black,5,.2,0,0);";
        drawingTools.setStyle(style0);

        // create the colour tools container, can create function to return based on name
        VBox pencolour_container = new VBox();
        Label pencolour_lbl = new Label("Pen Colour");
        ColorPicker pencolour_picker = new PenColourPicker(canvas);

        // add the pen colour components to the container
        ObservableList pencolours_list = pencolour_container.getChildren();
        pencolours_list.addAll(pencolour_lbl, pencolour_picker);

        VBox shapefill_container = new VBox();
        Label shapefill_lbl = new Label("Shape Fill Colour");
        ColorPicker shapefill_picker = new FillColourPicker(canvas);
        ObservableList shapefill_list = shapefill_container.getChildren();
        shapefill_list.addAll(shapefill_lbl, shapefill_picker);
        Separator sep = new Separator();
        String separator_style = "-prefWidth: 200.0";
        sep.setStyle(separator_style);


                // creating the list view for the undo stack, maybe store shapes?
        VBox undo_container = new VBox();
        Label undo_lbl = new Label("Undo History");
        ListView<String> undo_stack = new ListView<String>();
        ObservableList<String> action_list = FXCollections.observableArrayList("Instruction 1", "Instruction 2", "Instruction 3");
        undo_stack.setItems(action_list);
        ObservableList undo_container_contents = undo_container.getChildren();
        undo_container_contents.addAll(undo_lbl, undo_stack);

        // now put all the tools together
        Label drawingtools_lbl = new Label("Drawing Tools");
        VBox toolbar = new VBox();
        //looks like you can give elements ids and style using css file
        //style = "-fx-background-color: rgba(79, 84, 91, 1);";
        String style1="-fx-base: rgb(39, 40, 40); -fx-font-size: 12pt; -fx-background-color: linear-gradient(to bottom, derive(rgb(39, 40, 40),-30%), derive(rgb(39, 40, 40),-60%)),        linear-gradient(to bottom, rgb(74, 75, 78)2%, rgb(39, 40, 40) 98%); -fx-background-insets: 0, 0 0 1 0; -fx-padding: .9em 0.416667em .9em 0.416667em; -fx-effect: dropshadow(two-pass-box,black,5,.2,0,0);";
        toolbar.setStyle(style1);
        ObservableList toolbar_contents = toolbar.getChildren();
        toolbar_contents.addAll(drawingtools_lbl, pencolour_container, shapefill_container, undo_container);

        // also for functionality test click/hover event of canvas

        //idea for undo history stack -- get number value and then for loop recreate

        // look into extending graphics context for creating our shape objects

        //Setting the top, bottom, center, right and left nodes to the pane
       // window_container.setTop(menu_container);
        //window_container.setBottom(new TextField("Tools"));
        window_container.setRight(toolbar);
        window_container.setCenter(canvas);

        //Creating a scene object
        Scene scene = new Scene(window_container, 880, 800);


        //Adding scene to the stage
        stage.setScene(scene);

        //Setting title to the Stage
        stage.setTitle("Paint3000");



        //Displaying the contents of the stage
        stage.show();
    }


    public static void main(String args[]) {
        launch(args);
    }
}
