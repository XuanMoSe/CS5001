package model;

import java.awt.geom.Path2D;

public class Triangles extends Shapes {
    private final double half = 0.5;
    public Triangles() {
        shape = new Path2D.Double();
    }
    @Override
    public void construct(int x1, int y1, int x2, int y2) {
        double width = x2 - x1;
        Path2D.Double path = new Path2D.Double();
        path.moveTo(x1 + (half * width), y1);
        path.lineTo(x1, y2);
        path.lineTo(x2, y2);
        path.closePath();

        updateCoordinates(x1, y1, x2, y2);
        shape = path;
    }
}
