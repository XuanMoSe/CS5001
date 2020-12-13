package model;

import java.awt.geom.Ellipse2D;

public class Ellipses extends Shapes{
    public Ellipses() {
        shape = new Ellipse2D.Double();
    }

    @Override
    public void construct(int x1, int y1, int x2, int y2) {
        ((Ellipse2D.Double) shape).setFrameFromDiagonal(x1, y1, x2, y2);
        updateCoordinates(x1, y1, x2, y2);
    }
}
