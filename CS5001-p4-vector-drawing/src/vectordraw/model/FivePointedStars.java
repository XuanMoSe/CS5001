package model;

import java.awt.geom.Path2D;

public class FivePointedStars extends Shapes {
    private final double half = 0.5;
    private final double oneFifth = 0.2;
    private final double twoFifth = 0.4;
    private final double threeFifth = 0.6;
    private final double oneThird = 0.3;
    private final double quarter = 0.25;
    private final double threeQuarter = 0.75;

    public FivePointedStars() {
        shape = new Path2D.Double();
    }

    @Override
    public void construct(int x1, int y1, int x2, int y2) {
        double width = x2 - x1;
        double height = y2 - y1;
        Path2D.Double path = new Path2D.Double();

        path.moveTo(x1 + (half * width), y1);
        path.lineTo(x1 + (twoFifth * width), y1 + (twoFifth * height));
        path.lineTo(x1, y1 + (twoFifth * height));
        path.lineTo(x1 + (oneThird * width), y1 + (threeFifth * height));
        path.lineTo(x1 + (oneFifth * width), y2);
        path.lineTo(x1 + (half * width), y2 - (quarter * height));
        path.lineTo(x2 - (oneFifth * width), y2);
        path.lineTo(x2 - (oneThird * width), y2 - (twoFifth * height));
        path.lineTo(x2, y2 - (threeFifth * height));
        path.lineTo(x2 - (twoFifth * width), y2 - (threeFifth * height));
        path.closePath();

        updateCoordinates(x1, y1, x2, y2);
        shape = path;
    }
}
