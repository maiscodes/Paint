package paint;
import javafx.scene.canvas.*;


public class PaintCanvas extends Canvas {

    private int pixels;
    private GraphicsContext gc;

    public PaintCanvas(int pixels) {
        this.pixels = pixels;
        super.setWidth(pixels);
        super.setHeight(pixels);
        this.gc = this.getGraphicsContext2D();
    }

    public GraphicsContext getGraphicsContext(){
        return gc;
    }
}

