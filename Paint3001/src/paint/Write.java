package paint;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Write {
    /**
     * Given a PaintCanvas and File a .vec file will be made with instructions
     * from the given PaintCanvas and output to given File path
     *
     * @param canvas a PaintCanvas to take instructions from
     * @param file a File with path to write to
     */
    public static void write(PaintCanvas canvas, File file){
        BufferedWriter bw = null;
        try {
            if(!file.getPath().toUpperCase().endsWith(".VEC")){
                file = new File(file.getPath() + ".VEC");
            }
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            bw = new BufferedWriter(fw);
            for (Action instruction: canvas.getActions()
                 ){
                bw.write(instruction.printInstruction());
                bw.newLine();
            }
            System.out.println("File written Successfully");

        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        finally
        {
            try{
                if(bw!=null)
                    bw.close();
            }
            catch(Exception ex){
                System.out.println("Error in closing the BufferedWriter"+ex);
            }
        }
    }
}
