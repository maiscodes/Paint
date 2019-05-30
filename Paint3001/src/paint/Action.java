package paint;

import javafx.scene.canvas.GraphicsContext;

public abstract class Action {
    Action(){

    }

    public abstract void draw(GraphicsContext gc, double px);

    public abstract String printInstruction();
}