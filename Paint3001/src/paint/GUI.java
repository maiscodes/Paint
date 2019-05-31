package paint;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.stage.FileChooser;
import java.io.InputStream;

/**
 * Sets all GUI components and containers, sets scene, window, event handlers, styles, and option functionality
 */
public class GUI {
    private Stage stage;
    private Scene scene;
    private StackPane rootPane;
    private BorderPane window_container;
    private FlowPane menu_container;

    private HBox buttonBar;
    private Button open_btn;
    private Button save_btn;
    private Button new_btn;
    private Label menu_lbl;
    private ObservableList menuButtons;

    private PaintCanvas canvas;
    private PaintCanvas tempCanvas;
    private Pane canvasPane;

    private VBox drawingtoolbar;
    private ToolBar drawingTools;
    private HBox buttonBar2;
    private ToolBar toolBar;
    private Button line_btn;
    private Button plot_btn;
    private Button rect_btn;
    private Button ellipse_btn;
    private Button polygon_btn;

    private VBox pencolour_container;
    private Label pencolour_lbl;
    private ColorPicker pencolour_picker;
    private ObservableList pencolours_list;
    private VBox shapefill_container;
    private Label shapefill_lbl;
    private ColorPicker shapefill_picker;
    private ObservableList shapefill_list;

    private VBox undo_container;
    private ObservableList undo_container_contents;
    private  Label undo_lbl;
    private UndoHistoryListView<String> undo_stack;
    private ViewActionsButton view_btn;
    private UndoButton undo_btn;
    private RedoButton redo_btn;
    private FlowPane undo_btn_container;
    private ObservableList undo_btn_contents;

    final FileChooser fileChooser = new FileChooser();



    public GUI () {
        stage = new Stage();
        createUndoStack();
        createMenuBar();
        createCanvases();
        createUndoButtons();
        createToolButtons();
        createColourPickerTools();
        addEventHandlers();
        putAllDrawingToolsTogether();
        putGUIComponentsToWindow();
        setStyles();
        setupScene();
        stage.show();
    }
//Creates a button bar that allows the user to access "menu" functionality
    private void createMenuBar() {
        toolBar = new ToolBar();
        menu_container = new FlowPane();
        buttonBar = new HBox();
        buttonBar.getStyleClass().setAll("segmented-button-bar");
        open_btn = new Button("Open");
        save_btn = new Button("Save");
        new_btn = new Button("New");
        menu_lbl = new Label("Menu ");
        InputStream input6 = getClass().getResourceAsStream("menuicon.png");
        Image image6 = new Image(input6, 20, 20, true, true);
        ImageView imageView6 = new ImageView(image6);
        menu_lbl.setGraphic(imageView6);
        menu_lbl.setFont(new Font("Roboto", 17));

        open_btn.getStyleClass().addAll("first");
        new_btn.getStyleClass().addAll("last", "capsule");
        buttonBar.getChildren().addAll(open_btn, save_btn, new_btn);
        toolBar.getItems().addAll(buttonBar);

        menuButtons = menu_container.getChildren();
        menuButtons.addAll(menu_lbl, open_btn, save_btn, new_btn);
    }

    private void createUndoStack() {
        undo_lbl = new Label("Undo History");
        undo_lbl.setFont(new Font("Roboto", 17));
        InputStream input8 = getClass().getResourceAsStream("undoicon.png");
        Image image8 = new Image(input8, 20, 20, true, true);
        ImageView imageView8 = new ImageView(image8);
        undo_lbl.setGraphic(imageView8);

        undo_stack = new UndoHistoryListView<String>();
    }

    private void createUndoButtons(){
        view_btn = new ViewActionsButton(canvas, tempCanvas, undo_stack);
        InputStream input9 = getClass().getResourceAsStream("hideicon.png");
        Image image9 = new Image(input9, 25, 25, true, true);
        ImageView imageView9 = new ImageView(image9);
        view_btn.setGraphic(imageView9);


        undo_btn = new UndoButton(canvas);
        InputStream input10 = getClass().getResourceAsStream("undobutton.png");
        Image image10 = new Image(input10, 25, 25, true, true);
        ImageView imageView10 = new ImageView(image10);
        undo_btn.setGraphic(imageView10);

        redo_btn = new RedoButton(canvas);
        InputStream input11 = getClass().getResourceAsStream("redobutton.png");
        Image image11 = new Image(input11, 25, 25, true, true);
        ImageView imageView11 = new ImageView(image11);
        redo_btn.setGraphic(imageView11);

        undo_btn_container = new FlowPane();
        undo_btn_contents = undo_btn_container.getChildren();
        undo_btn_contents.addAll(view_btn, undo_btn, redo_btn);
        undo_container = new VBox();
        undo_container_contents = undo_container.getChildren();
        undo_container_contents.addAll(undo_lbl, undo_stack, undo_btn_container);
    }

    private void createCanvases(){
        canvasPane = new Pane();
        canvas = new PaintCanvas(500, undo_stack);
        tempCanvas = new PaintCanvas(500, undo_stack);

        // make resizeable
        canvas.widthProperty().bind(canvasPane.widthProperty());
        canvas.heightProperty().bind(canvasPane.heightProperty());
        tempCanvas.widthProperty().bind(canvasPane.widthProperty());
        tempCanvas.heightProperty().bind(canvasPane.heightProperty());

        //add to a pane
        canvasPane.getChildren().add(canvas);
        canvasPane.getChildren().add(tempCanvas);
        canvas.toFront();
    }
//creates drawing tool buttons with image styling, that allows the user to access shape types
    private void createToolButtons(){
        line_btn = new PaintButton(ShapeType.LINE, canvas);
        InputStream input = getClass().getResourceAsStream("line.png");
        Image image = new Image(input, 40, 40, true, true);
        ImageView imageView = new ImageView(image);
        line_btn.setGraphic(imageView);


        plot_btn = new PaintButton(ShapeType.PLOT, canvas);
        InputStream input2 = getClass().getResourceAsStream("dot2.jpg");
        Image image2 = new Image(input2, 40, 40, true, true);
        ImageView imageView2 = new ImageView(image2);
        plot_btn.setGraphic(imageView2);

        rect_btn = new PaintButton(ShapeType.RECTANGLE, canvas);
        InputStream input3 = getClass().getResourceAsStream("square2.png");
        Image image3 = new Image(input3, 58, 58, true, true);
        ImageView imageView3 = new ImageView(image3);
        rect_btn.setGraphic(imageView3);


        ellipse_btn = new PaintButton(ShapeType.ELLIPSE, canvas);
        InputStream input4 = getClass().getResourceAsStream("oval.png");
        Image image4 = new Image(input4, 50, 50, true, true);
        ImageView imageView4 = new ImageView(image4);
        ellipse_btn.setGraphic(imageView4);

        polygon_btn = new PaintButton(ShapeType.POLYGON, canvas);
        InputStream input5 = getClass().getResourceAsStream("hexy.png");
        Image image5 = new Image(input5, 40, 40, true, true);
        ImageView imageView5 = new ImageView(image5);
        polygon_btn.setGraphic(imageView5);
    }

    public void createColourPickerTools(){
        pencolour_container = new VBox();
        pencolour_lbl = new Label("Pen Colour");
        pencolour_picker = new PenColourPicker(canvas, undo_stack);

        // add the pen colour components to the container
        pencolours_list = pencolour_container.getChildren();
        pencolours_list.addAll(pencolour_lbl, pencolour_picker);

        shapefill_container = new VBox();
        shapefill_lbl = new Label("Shape Fill Colour");
        shapefill_picker = new FillColourPicker(canvas, undo_stack);
        shapefill_list = shapefill_container.getChildren();
        shapefill_list.addAll(shapefill_lbl, shapefill_picker);
    }

    private void addEventHandlers() {
        open_btn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        openVECfile();
                    }
                });



        save_btn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        saveVECfile();
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
                        if(t.getCode()== KeyCode.Z){
                            canvas.undoAction();
                            System.out.println("Action undone!");
                        }
                        if(t.getCode()== KeyCode.Y){
                            canvas.redoAction();
                            System.out.println("Action redone!");
                        }
                        if(t.getCode()== KeyCode.N){
                            openNewWindow();
                        }
                        if(t.getCode()== KeyCode.S){
                            saveVECfile();
                        }
                        if(t.getCode()== KeyCode.O){
                            openVECfile();
                        }
                    }
                });

        new_btn.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        openNewWindow();
                    }
                });
    }

    private void putAllDrawingToolsTogether(){
        drawingTools = new ToolBar();
        buttonBar2 = new HBox();
        buttonBar2.getStyleClass().setAll("segmented-button-bar-2");
        buttonBar2.getChildren().addAll(line_btn, plot_btn, rect_btn, ellipse_btn, polygon_btn);
        drawingTools.getItems().addAll(buttonBar2);
        Label drawingtools_lbl = new Label("Colour Palette");
        drawingtools_lbl.setFont(new Font("Roboto", 17));
        InputStream input7 = getClass().getResourceAsStream("colouricon.png");
        Image image7 = new Image(input7, 30, 30, true, true);
        ImageView imageView7 = new ImageView(image7);
        drawingtools_lbl.setGraphic(imageView7);

        drawingtoolbar = new VBox();
        ObservableList drawingtoolbar_contents = drawingtoolbar.getChildren();
        drawingtoolbar_contents.addAll(drawingtools_lbl, pencolour_container, shapefill_container, undo_container, view_btn);
    }

    private void putGUIComponentsToWindow(){
        rootPane = new StackPane();
        window_container = new BorderPane();
        window_container.setId("background");

        window_container.setTop(toolBar);
        rootPane.getChildren().addAll(window_container, menu_container);

        window_container.setBottom(drawingTools);

        window_container.setTop(menu_container);

        window_container.setCenter(canvasPane);

        window_container.setRight(drawingtoolbar);
    }

    private void setupScene(){
        scene = new Scene(rootPane);

        stage.setTitle("Paint3000");

        stage.setScene(scene);
    }

    private void setStyles() {
        String base_style = "-fx-background-color: #02031E;";
        rootPane.setStyle(base_style);

        String style="-fx-base: rgb(39, 40, 40); -fx-font-size: 12pt; -fx-background-color: linear-gradient(to bottom, derive(rgb(39, 40, 40),-30%), derive(rgb(39, 40, 40),-60%)),        linear-gradient(to bottom, rgb(74, 75, 78)2%, rgb(39, 40, 40) 98%); -fx-background-insets: 0, 0 0 1 0; -fx-padding: .9em 0.416667em .9em 0.416667em; -fx-effect: dropshadow(two-pass-box,black,5,.2,0,0);";
        menu_container.setStyle(style);

        String style1="-fx-base: rgb(39, 40, 40); -fx-font-size: 12pt; -fx-background-color: linear-gradient(to bottom, derive(rgb(39, 40, 40),-30%), derive(rgb(39, 40, 40),-60%)),        linear-gradient(to bottom, rgb(74, 75, 78)2%, rgb(39, 40, 40) 98%); -fx-background-insets: 0, 0 0 1 0; -fx-padding: .9em 0.416667em .9em 0.416667em; -fx-effect: dropshadow(two-pass-box,black,5,.2,0,0);";
        drawingtoolbar.setStyle(style1);

        String style0="-fx-base: rgb(39, 40, 40); -fx-font-size: 12pt; -fx-background-color:  #04052E; -fx-background-insets: 0, 0 0 1 0; -fx-padding: .9em 0.416667em .9em 0.416667em; -fx-effect: dropshadow(two-pass-box,black,5,.2,0,0);";
        drawingTools.setStyle(style0);

    }

    private void openVECfile(){
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("VEC files", "*.vec"));
        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            PaintCanvas canvas = this.canvas;
            UndoHistoryListView undo_stack = this.undo_stack;
            if(this.canvas.getActions().size() > 0){
                GUI newWindow = new GUI();
                canvas = newWindow.canvas;
                undo_stack = newWindow.undo_stack;
            }

            try{
                Read.read(canvas, file);
            } catch (Exception error){
                error.printStackTrace();
            }
            canvas.redraw();
            undo_stack.updateHistoryListView(canvas.getActions());
        }

    }

    private void saveVECfile(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save Drawing");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("VEC files (*.vec)", "*.vec");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(stage);
        if (file != null) {
            Write.write(canvas, file);
        }
    }

    private void openNewWindow(){
        GUI newWindow = new GUI();
    }


}
