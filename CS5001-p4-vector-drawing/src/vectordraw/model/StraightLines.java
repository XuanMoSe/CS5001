package model;

import util.SetOfConstant;

import java.awt.*;
import java.awt.geom.Line2D;
import java.io.FileReader;
import java.io.IOException;

public class StraightLines extends Shapes {

    public StraightLines(FileReader fileReader) throws IOException {
        super(fileReader);
    }
    public StraightLines() {
        super();
        /*Init a line detail see the Java doc about shape*/
        shape = new Line2D.Double();
    }
    @Override
    public void construct(int x1, int y1, int x2, int y2) {
        ((Line2D.Double) shape).setLine(x1, y1, x2, y2);
        updateCoordinates(x1, y1, x2, y2);
    }
    @Override
    public boolean isClicked(int x, int y) {
        /**
         * More detail why I used intersects, not used contain cause it is line,you could check in Java doc
         * Here is the link for the shape java doc. So tired to read java doc.
         * https://docs.oracle.com/javase/8/docs/api/java/awt/Shape.html#intersects-double-double-double-double-
         * But this method still not really precise.
         */
        return shape.intersects(x, y, SetOfConstant.INTERSECT_AREA, SetOfConstant.INTERSECT_AREA);
    }
    @Override
    public void paint(Graphics2D g2D) {
        if (getColor() != null) {
            g2D.setColor(getColor());
        }
        g2D.draw(getShape());
    }
}
