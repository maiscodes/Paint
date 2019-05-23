package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class PaintPolygon extends Shape {

    public PaintPolygon(){
        super(ShapeType.POLYGON);
    }

    public void draw(GraphicsContext gc, double px){
        double[] x_coords = new double[x_coord.size()];
        double[] y_coords = new double[y_coord.size()];

        for (int i = 0; i < x_coords.length; i++) {
            x_coords[i] = Double.valueOf(x_coord.get(i) * px);
            y_coords[i] = Double.valueOf(y_coord.get(i) * px);
        }

        if (gc.getFill() == Color.TRANSPARENT) {
            gc.strokePolygon(x_coords, y_coords, x_coords.length);
        }
        else {
            gc.fillPolygon(x_coords, y_coords, x_coords.length);
            gc.strokePolygon(x_coords, y_coords, x_coords.length);
        }

    }

    public String printInstruction(){
        // instructions don't print out the original coordinate at the end, will need to reappend when reading from file
        double x;
        double y;
        String instruction = this.shape_type.toString();

        for (int p = 0; p < x_coord.size() - 2; p++) {
            x = Double.valueOf(x_coord.get(p));
            y = Double.valueOf(y_coord.get(p));
            instruction += String.format(" %.2f %.2f", x, y);
        }
        System.out.println(instruction);
        return instruction;
    }

    public void setLastCoord(double x, double y){
        x_coord.set(x_coord.size() - 1, checkXY(x));
        y_coord.set(y_coord.size() - 1, checkXY(y));
    }
}
