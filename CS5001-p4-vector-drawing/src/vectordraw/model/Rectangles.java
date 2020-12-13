package model;

import java.awt.geom.Path2D;

public class Rectangles extends Shapes {
    public Rectangles() {

    }

    @Override
    public void construct(int x1, int y1, int x2, int y2) {
        Path2D.Double rect = new Path2D.Double();

        rect.moveTo(x1, y1);
        rect.lineTo(x2, y1);
        rect.lineTo(x2, y2);
        rect.lineTo(x1, y2);
        rect.lineTo(x1, y1);
        rect.closePath();

        updateCoordinates(x1, y1, x2, y2);
        shape = rect;
    }
}
