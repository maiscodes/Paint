package paint;

import javafx.scene.paint.Color;
import java.io.*;

/**
 * Reads contents of file path and outputs instructions to Canvas
 */
public class Read{

    /** Given a PaintCanvas and File, the function will read the contents of the given File path
     * and output instructions to the given PaintCanvas
     *
     * @param canvas PaintCanvas to output instructions to
     * @param file File path to read
     * @throws vecExceptions exception relating to unknown instructions or incorrect file type
     * @throws IOException exception wherein file does not exist
     */
    public static void read(PaintCanvas canvas, File file) throws vecExceptions, IOException{
        if (!(file.getPath().toUpperCase().endsWith(".VEC"))) {
            throw new vecExceptions("File not .VEC");
        }
        BufferedReader reader = null;
        double canvasSize = canvas.getHeight();

        try {
            //File file = new File("files/test.vec");
            reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null){
                String[] content = line.split(" ");
                if(content[0].equals("RECTANGLE")){
                    Shape rectangle = new PaintRectangle();
                    rectangle.addXCoord(Double.parseDouble(content[1]));
                    rectangle.addYCoord(Double.parseDouble(content[2]));
                    rectangle.addXCoord(Double.parseDouble(content[3]));
                    rectangle.addYCoord(Double.parseDouble(content[4]));

                    canvas.addToActions(rectangle);
                }
                else if(content[0].equals("ELLIPSE")){
                    Shape ellipse = new PaintEllipse();
                    ellipse.addXCoord(Double.parseDouble(content[1]));
                    ellipse.addYCoord(Double.parseDouble(content[2]));
                    ellipse.addXCoord(Double.parseDouble(content[3]));
                    ellipse.addYCoord(Double.parseDouble(content[4]));

                    canvas.addToActions(ellipse);
                }
                else if(content[0].equals("PLOT")){
                    Shape plot = new PaintPlot();
                    plot.addXCoord(Double.parseDouble(content[1]));
                    plot.addYCoord(Double.parseDouble(content[2]));

                    canvas.addToActions(plot);
                }
                else if(content[0].equals("POLYGON")){
                    Shape polygon = new PaintPolygon();
                    for(int x = 1; x < content.length; x++){
                        if(!(x % 2 == 0)) {
                            polygon.addXCoord(Double.parseDouble(content[x]));
                        }
                        else {
                            polygon.addYCoord(Double.parseDouble(content[x]));
                        }
                    }
                    polygon.addXCoord(Double.parseDouble(content[1]));
                    polygon.addYCoord(Double.parseDouble(content[2]));
                    canvas.addToActions(polygon);
                }
                else if(content[0].equals("LINE")){
                    Shape lineShape = new PaintLine();
                    lineShape.addXCoord(Double.parseDouble(content[1]));
                    lineShape.addYCoord(Double.parseDouble(content[2]));
                    lineShape.addXCoord(Double.parseDouble(content[3]));
                    lineShape.addYCoord(Double.parseDouble(content[4]));

                    canvas.addToActions(lineShape);

                }
                else if(content[0].equals("PEN")){
                    SetPen color = new SetPen(Color.valueOf(content[1]));

                    canvas.addToActions(color);
                }
                else if(content[0].equals("FILL")){
                    //SetFill color = new SetFill(Color.valueOf(content[1]));
                    SetFill color;
                    if(content[1].equals("OFF")){
                        color = new SetFill(Color.rgb(0,0,0,0));
                    }
                    else{
                        color = new SetFill(Color.valueOf(content[1]));
                    }
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

