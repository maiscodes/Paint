package paint;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public class PaintPolygon extends Shape {

    public PaintPolygon(){
        super(ShapeType.POLYGON);
    }

    /**
     * Given the graphics context and size of the canvas,
     * a polygon is drawn onto the graphics context.
     * @param gc graphics context of the canvas which the polygon will be drawn on
     * @param px the size in pixels of the canvas
     */
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

    /**
     * Prints the instruction using and x and y coordinate properties
     * of this shape.
     * @return string containing instructions with POLYGON key word
     * and all of the polygon's coordinates in order as they were drawn.
     */
    public String printInstruction(){
        double x;
        double y;
        String instruction = this.shape_type.toString();

        for (int p = 0; p < x_coord.size() - 1; p++) {
            x = Double.valueOf(x_coord.get(p));
            y = Double.valueOf(y_coord.get(p));
            instruction += String.format(" %.2f %.2f", x, y);
        }
        System.out.println(instruction);
        return instruction;
    }

    /**
     * Given new x and y coordinates of the polygon,
     * set the most recent x and y properties of the polygon
     * to the given values.
     * @param x double representing the x coordinate of the last vertex of the polygon
     * @param y double representing the y coordinate of the last vertex of the polygon
     */
    public void setLastCoord(double x, double y){
        x_coord.set(x_coord.size() - 1, checkXY(x));
        y_coord.set(y_coord.size() - 1, checkXY(y));
    }
}
