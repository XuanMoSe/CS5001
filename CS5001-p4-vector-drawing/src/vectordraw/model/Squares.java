package model;

import util.SetOfConstant;

public class Squares extends Rectangles {
    @Override
    public void construct(int x1, int y1, int x2, int y2) {
        /*find the short side*/
        int shortSide = Math.min(Math.abs(x2 - x1), Math.abs(y2 - y1));
        int newX = x1 > x2 ? x1 - shortSide : x1;
        int newY = y1 > y2 ? y1 - shortSide : y1;

        /*use the super method with the side limit*/
        super.construct(newX, newY, newX + shortSide, newY + shortSide);
    }

    @Override
    public void resize(int x, int y, int corner) {
        int shortSide = 0;

        if (corner == SetOfConstant.BOTTOM_RIGHT_CORNER) {
            shortSide = Math.min(Math.abs(x - x1), Math.abs(y - y1));
            super.construct(x1, y1, x1 + shortSide, y1 + shortSide);

        }
        else if (corner == SetOfConstant.UPPER_LEFT_CORNER) {
            shortSide = Math.min(Math.abs(x - x2), Math.abs(y - y2));
            super.construct(x2 - shortSide, y2 - shortSide, x2, y2);

        }
        else if (corner == SetOfConstant.UPPER_RIGHT_CORNER) {
            shortSide = Math.min(Math.abs(x - x1), Math.abs(y - y2));
            super.construct(x1, y2 - shortSide, x1 + shortSide, y2);
        }
        else if (corner == SetOfConstant.BOTTOM_LEFT_CORNER) {
            shortSide = Math.min(Math.abs(x - x2), Math.abs(y - y1));
            super.construct(x2 - shortSide, y1, x2, y1 + shortSide);
        }
    }
}
