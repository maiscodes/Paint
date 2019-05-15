package paint;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.event.EventHandler;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.paint.*;
import javafx.scene.canvas.*;
import javafx.scene.control.ListView;

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

        //create tool buttons pane
        HBox drawingtools_container = new HBox();

        // create the buttons
        Button line_btn = new Button("LINE");
        Button plot_btn = new Button("PLOT");
        Button rect_btn = new Button("RECT");
        Button ellipse_btn = new Button("ELLIPSE");
        Button polygon_btn = new Button("POLYGON");

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
        ColorPicker pencolour_picker = new ColorPicker();

        // add the pen colour components to the container
        ObservableList pencolours_list = pencolour_container.getChildren();
        pencolours_list.addAll(pencolour_lbl, pencolour_picker);

        VBox shapefill_container = new VBox();
        Label shapefill_lbl = new Label("Shape Fill Colour");
        ColorPicker shapefill_picker = new ColorPicker();
        ObservableList shapefill_list = shapefill_container.getChildren();
        shapefill_list.addAll(shapefill_lbl, shapefill_picker);

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
        style = "-fx-background-color: rgba(79, 84, 91, 1);";
        toolbar.setStyle(style);
        ObservableList toolbar_contents = toolbar.getChildren();
        toolbar_contents.addAll(drawingtools_lbl, drawingtools_container, pencolour_container, shapefill_container, undo_container);

        // create canvas, coordinates works from top left
        PaintCanvas canvas = new PaintCanvas(400);
        //make it white for now
        GraphicsContext gc = canvas.getGraphicsContext();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 400, 400);


        // testing the default shape tools provided
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);

        //line
        gc.strokeLine(40, 10, 10, 40);


        //test out paintLine class also when storing out actions, maybe another class??
        // canvas should store current colour, pen colour and count
        Shape testLine = new PaintLine();
        testLine.addXCoord(300);
        testLine.addYCoord(300);
        testLine.addXCoord(300);
        testLine.addYCoord(350);
        testLine.printInstruction();
        //testLine.draw(gc);


        //test out paintRectangle class
        //gc.setFill(Color.RED);
        SetFill fill1 = new SetFill(Color.RED);
        Shape testRect = new PaintRectangle();
        testRect.addXCoord(100);
        testRect.addYCoord(100);
        testRect.addXCoord(200);
        testRect.addYCoord(200);
        //testRect.draw(gc);

        //gc.setFill(Color.TRANSPARENT);
        SetFill fill2 = new SetFill(Color.TRANSPARENT);
        Shape testRect1 = new PaintRectangle();
        testRect1.addXCoord(200);
        testRect1.addYCoord(200);
        testRect1.addXCoord(300);
        testRect1.addYCoord(300);
        //testRect1.draw(gc);

        //test ellipse
        SetFill fill3 = new SetFill(Color.ORANGE);
        SetStroke stroke1 = new SetStroke(Color.PURPLE);
        Shape testEllipse = new PaintEllipse();
        testEllipse.addXCoord(100);
        testEllipse.addYCoord(50);
        testEllipse.addXCoord(200);
        testEllipse.addYCoord(100);


        //test Polygon
        SetFill fill4 = new SetFill(Color.GREEN);
        SetStroke stroke2 = new SetStroke(Color.RED);
        Shape testPolygon = new PaintPolygon();
        testPolygon.addXCoord(100);
        testPolygon.addYCoord(300);
        testPolygon.addXCoord(300);
        testPolygon.addYCoord(300);
        testPolygon.addXCoord(200);
        testPolygon.addYCoord(100);
        testPolygon.addXCoord(100);
        testPolygon.addYCoord(300);

        //test plot
        Shape testPlot = new PaintPlot();
        testPlot.addXCoord(350);
        testPlot.addYCoord(350);


        //okay time to test if can store in array now colours
        ArrayList<Action> actions = new ArrayList<>();
        actions.add(testLine);
        actions.add(fill1);
        actions.add(testRect);
        actions.add(fill2);
        actions.add(testRect1);
        actions.add(fill3);
        actions.add(stroke1);
        actions.add(testEllipse);
        actions.add(fill4);
        actions.add(stroke2);
        actions.add(testPolygon);
        actions.add(testPlot);

        // for history stack, get value of instruction and then create new canvas and render to that point
        for (int a = 0; a < actions.size(); a++) {
            actions.get(a).draw(gc);
            actions.get(a).printInstruction();
        }


        //rectangle
        gc.fillRect(110, 60, 30, 30);
        gc.strokeRect(160, 60, 30, 30);

        //ellipse so fill - when not transparent, stroke when transparent
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);

        //polygon
        gc.strokePolygon(new double[]{60, 90, 60, 90},
                new double[]{210, 210, 240, 240}, 4);


        // also for functionality test click/hover event of canvas

        //idea for undo history stack -- get number value and then for loop recreate

        // look into extending graphics context for creating our shape objects

        //Setting the top, bottom, center, right and left nodes to the pane
        window_container.setTop(menu_container);
        //window_container.setBottom(new TextField("Tools"));
        window_container.setLeft(toolbar);
        window_container.setRight(new TextField("Zoom"));
        window_container.setCenter(canvas);

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
