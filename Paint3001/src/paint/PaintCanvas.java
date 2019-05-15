package paint;
import javafx.scene.canvas.*;


public class PaintCanvas extends Canvas {

    private int pixels;

    public PaintCanvas(int pixels) {
        this.pixels = pixels;
        super.setWidth(pixels);
        super.setHeight(pixels);
    }
}

