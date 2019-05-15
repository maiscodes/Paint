package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PaintPolygon extends Shape {

    public PaintPolygon(){
        super(ShapeType.POLYGON);
    }

    public void draw(GraphicsContext gc){
        double[] x_coords = new double[x_coord.size()];
        double[] y_coords = new double[y_coord.size()];

        for (int i = 0; i < x_coords.length; i++) {
            x_coords[i] = Double.valueOf(x_coord.get(i));
            y_coords[i] = Double.valueOf(y_coord.get(i));
        }

        if (gc.getFill() == Color.TRANSPARENT) {
            gc.strokePolygon(x_coords, y_coords, x_coords.length - 1);
        }
        else {
            gc.fillPolygon(x_coords, y_coords, x_coords.length - 1);
            gc.strokePolygon(x_coords, y_coords, x_coords.length - 1);
        }

    };

    public String printInstruction(){
        // instructions don't print out the original coordinate at the end, will need to reappend when reading from file
        double x;
        double y;
        String instruction = String.format("POLYGON");

        for (int p = 0; p < x_coord.size() - 1; p++) {
            x = Double.valueOf(x_coord.get(p))/canvas_px;
            y = Double.valueOf(y_coord.get(p))/canvas_px;
            instruction += String.format(" %.2f %.2f", x, y);
        }
        System.out.println(instruction);
        return instruction;
    }
}
