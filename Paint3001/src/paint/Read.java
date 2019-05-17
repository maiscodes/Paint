package paint;

import javafx.scene.paint.Color;
import java.io.*;

public class Read{

    public static void read(PaintCanvas canvas){
        BufferedReader reader = null;

        try {
            File file = new File("files/test.vec");
            reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null){
                String[] content = line.split(" ");
                if(content[0].equals("RECTANGLE")){
                    Shape rectangle = new PaintRectangle();
                    rectangle.addXCoord(Double.parseDouble(content[1]) * 500);
                    rectangle.addYCoord(Double.parseDouble(content[2]) * 500);
                    rectangle.addXCoord(Double.parseDouble(content[3]) * 500);
                    rectangle.addYCoord(Double.parseDouble(content[4]) * 500);

                    canvas.addToActions(rectangle);
                }
                else if(content[0].equals("ELLIPSE")){
                    Shape ellipse = new PaintEllipse();
                    ellipse.addXCoord(Double.parseDouble(content[1]) * 500);
                    ellipse.addYCoord(Double.parseDouble(content[2]) * 500);
                    ellipse.addXCoord(Double.parseDouble(content[3]) * 500);
                    ellipse.addYCoord(Double.parseDouble(content[4]) * 500);

                    canvas.addToActions(ellipse);
                }
                else if(content[0].equals("PLOT")){
                    Shape plot = new PaintPlot();
                    plot.addXCoord(Double.parseDouble(content[1]) * 500);
                    plot.addYCoord(Double.parseDouble(content[2]) * 500);
                }
                else if(content[0].equals("POLYGON")){

                }
                else if(content[0].equals("LINE")){
                    Shape lineShape = new PaintLine();
                    lineShape.addXCoord(Double.parseDouble(content[1]) * 500);
                    lineShape.addYCoord(Double.parseDouble(content[2]) * 500);
                    lineShape.addXCoord(Double.parseDouble(content[3]) * 500);
                    lineShape.addYCoord(Double.parseDouble(content[4]) * 500);

                    canvas.addToActions(lineShape);

                }
                else if(content[0].equals("PEN")){
                    SetPen color = new SetPen(Color.valueOf(content[1]));

                    canvas.addToActions(color);
                }
                else if(content[0].equals("FILL")){
                    SetFill color = new SetFill(Color.valueOf(content[1]));

                    canvas.addToActions(color);
                }
                else{
                    throw new vecExceptions("File contains unknown instruction");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

class vecExceptions extends Exception{
    public vecExceptions(String s){
        super(s);
    }
}
