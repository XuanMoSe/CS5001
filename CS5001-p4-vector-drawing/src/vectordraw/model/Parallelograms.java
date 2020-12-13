package model;

import java.awt.geom.Path2D;

public class Parallelograms extends Shapes {
    private final double oneThird = 0.3;
    public Parallelograms() {
        shape = new Path2D.Double();
    }
    @Override
    public void construct(int x1, int y1, int x2, int y2) {
        double width = x2 - x1;
        double height = y2 - y1;
        Path2D.Double path = new Path2D.Double();
        path.moveTo(x1 + (oneThird * width), y1);
        path.lineTo(x1, y2);
        path.lineTo(x2 - (oneThird * width), y2);
        path.lineTo(x2, y1);
        path.closePath();

        updateCoordinates(x1, y1, x2, y2);
        shape = path;
    }
}
