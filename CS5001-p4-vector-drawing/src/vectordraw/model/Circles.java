package model;

import util.SetOfConstant;

public class Circles extends Ellipses {
    /**
     * construct circles
     * @param x1 mouse start point x value.
     * @param y1 mouse start point y value.
     * @param x2 mouse end point x value.
     * @param y2 mouse end point y value.
     */
    @Override
    public void construct(int x1, int y1, int x2, int y2) {
        /*find out the x and y components of the radius as the shortest side*/
        int radius = Math.min(Math.abs(x2 - x1), Math.abs(y2 - y1));

        /*use the ellipse method to draw the circle using the x and y component of the radius*/
        int newX = x1 > x2 ? x1 - radius : x1;
        int newY = y1 > y2 ? y1 - radius : y1;

        super.construct(newX, newY, newX + radius, newY + radius);
    }

    @Override
    public void resize(int x, int y, int corner) {
        int s = 0;
        if (corner == SetOfConstant.BOTTOM_RIGHT_CORNER) {
            s = Math.min(Math.abs(x - x1), Math.abs(y - y1));
            super.construct(x1, y1, x1 + s, y1 + s);

        }
        else if (corner == SetOfConstant.UPPER_LEFT_CORNER) {
            s = Math.min(Math.abs(x - x2), Math.abs(y - y2));
            super.construct(x2 - s, y2 - s, x2, y2);

        }
        else if (corner == SetOfConstant.UPPER_RIGHT_CORNER) {
            s = Math.min(Math.abs(x - x1), Math.abs(y - y2));
            super.construct(x1, y2 - s, x1 + s, y2);
        }
        else if (corner == SetOfConstant.BOTTOM_LEFT_CORNER) {
            s = Math.min(Math.abs(x - x2), Math.abs(y - y1));
            super.construct(x2 - s, y1, x2, y1 + s);
        }
    }

}
