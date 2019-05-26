package Tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import paint.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ShapeTest {

    Shape shape;
    double testCoords[] = {0.10, 0.20, 0.30, 1.20, -0.10, 0.20, 0.50};

    @BeforeEach
    public void setUp(){
        shape = null;
        //{0.10, 0.20, 0.30, 1.00, 0.00};
    }

    @Test
    public void shapeXTest(){
        shape = new PaintPolygon();
        ArrayList<Double> expected = new ArrayList<>();
        expected.add(0.10);
        expected.add(0.20);
        expected.add(0.30);
        expected.add(1.00);
        expected.add(0.00);
        expected.add(0.20);
        expected.add(0.50);

        for (double x:testCoords
             ) {
            shape.addXCoord(x);
        }

        assertEquals(expected, shape.getXCoords());
    }

    @Test
    public void shapeYTest(){
        shape = new PaintPolygon();
        ArrayList<Double> expected = new ArrayList<>();
        expected.add(0.10);
        expected.add(0.20);
        expected.add(0.30);
        expected.add(1.00);
        expected.add(0.00);
        expected.add(0.20);
        expected.add(0.50);

        for (double y:testCoords
        ) {
            shape.addYCoord(y);
        }

        assertEquals(expected, shape.getYCoords());
    }

    @Test
    public void testEllipse(){
        shape = new PaintEllipse();

        shape.addXCoord(0.60);
        shape.addXCoord(0.70);
        shape.addYCoord(0.20);
        shape.addYCoord(0.80);

        shape.setX1Coord(0.00);
        shape.setY1Coord(1.20);
        shape.setX2Coord(0.20);
        shape.setY2Coord(-0.80);

        String expected = "ELLIPSE 0.00 1.00 0.20 0.00";

        assertEquals(expected, shape.printInstruction());
    }

    @Test
    public void testLine(){
        shape = new PaintLine();

        shape.addXCoord(0.60);
        shape.addXCoord(0.70);
        shape.addYCoord(0.20);
        shape.addYCoord(0.80);

        shape.setX1Coord(0.00);
        shape.setY1Coord(1.20);
        shape.setX2Coord(0.20);
        shape.setY2Coord(-0.80);

        String expected = "LINE 0.00 1.00 0.20 0.00";

        assertEquals(expected, shape.printInstruction());

    }
    @Test
    public void testPlot(){
        shape = new PaintPlot();

        shape.addXCoord(0.60);
        shape.addYCoord(0.80);

        shape.setX1Coord(0.00);
        shape.setY1Coord(-0.80);

        String expected = "PLOT 0.00 0.00";

        assertEquals(expected, shape.printInstruction());

    }
    @Test
    public void testPolygon(){
        PaintPolygon shape = new PaintPolygon();

        for (double c:testCoords
             ) {
            shape.addXCoord(c);
            shape.addYCoord(c);
        }

        //{0.10, 0.20, 0.30, 1.20, -0.10, 0.20, 0.50};

        shape.setX1Coord(0.00);
        shape.setY1Coord(1.20);
        shape.setX2Coord(0.20);
        shape.setY2Coord(-0.80);

        shape.setLastCoord(0.99, 0.99);

        shape.addXCoord(0.10);
        shape.addYCoord(0.10);

        String expected = "POLYGON 0.00 1.00 0.20 0.00 0.30 0.30 1.00 1.00 0.00 0.00 0.20 0.20 0.99 0.99";

        assertEquals(expected, shape.printInstruction());
    }
    @Test
    public void testRectangle(){
        shape = new PaintRectangle();

        shape.addXCoord(0.60);
        shape.addXCoord(0.70);
        shape.addYCoord(0.20);
        shape.addYCoord(0.80);

        shape.setX1Coord(0.00);
        shape.setY1Coord(1.20);
        shape.setX2Coord(0.20);
        shape.setY2Coord(-0.80);

        String expected = "RECTANGLE 0.00 1.00 0.20 0.00";

        assertEquals(expected, shape.printInstruction());

    }

}