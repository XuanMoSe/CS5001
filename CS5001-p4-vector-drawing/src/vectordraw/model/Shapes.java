package model;

import util.SetOfConstant;

import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;

public abstract class Shapes implements Serializable {
/*Useful Link to Java doc https://docs.oracle.com/javase/8/docs/api/*/
    protected Shape shape;

    protected int x1 = 0;

    protected int y1 = 0;

    protected int x2 = 0;

    protected int y2 = 0;

    protected int width = 0;

    protected int height = 0;

    private Color color;

    private boolean fill = false;

    public Shapes() {

    }

    public Shapes(FileReader fileReader) throws IOException {
        char[] c = new char[10];
        fileReader.read(c, 0, 10);
        color = new Color(Integer.parseInt(new String(c)));
        fileReader.read(c, 0, 10);
        fileReader.read(c, 0, 10);
        int x1 = Integer.parseInt(new String(c));
        fileReader.read(c, 0, 10);
        int y1 = Integer.parseInt(new String(c));
        this.x1 = x1;
        this.y1 = y1;
        fileReader.read(c, 0, 10);
        int x2 = Integer.parseInt(new String(c));
        fileReader.read(c, 0, 10);
        int y2 = Integer.parseInt(new String(c));
        this.x2 = x2;
        this.y2 = y2;
        width = Math.abs(x2 - x1);
        height = Math.abs(y2 - y1);
    }

    public boolean isClicked(int x, int y) {
        return (shape.contains(x, y));
    }

    public abstract void construct(int x1, int y1, int x2, int y2);

    public void dragTo(int x, int y) {
        construct(x - (width / 2), y - (height / 2), x + (width / 2), y + (height / 2));
    }

    public void resize(int x, int y, int corner) {
        switch(corner) {
            case SetOfConstant.UPPER_LEFT_CORNER:
                construct(x, y, x2, y2);
                break;

            case SetOfConstant.UPPER_RIGHT_CORNER:
                construct(x1, y, x, y2);
                break;

            case SetOfConstant.BOTTOM_LEFT_CORNER:
                construct(x, y1, x2, y);
                break;

            case SetOfConstant.BOTTOM_RIGHT_CORNER:
                construct(x1, y1, x, y);
                break;

            default:
                break;
        }
    }
    /**
     * update boundary
     */
    public void updateBounds() {
        x1 = (int) shape.getBounds().getMinX();
        y1 = (int) shape.getBounds().getMinY();
        x2 = (int) shape.getBounds().getMaxX();
        y2 = (int) shape.getBounds().getMaxY();
        width = x2 - x1;
        height = y2 - y1;
    }

    public void paint(Graphics2D g2D) {
        if (color != null) {
            g2D.setColor(color);
        }
        if (fill) {
            g2D.fill(shape);
        }
        g2D.draw(shape);
    }

    protected void updateCoordinates(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        width = x2 - x1;
        height = y2 - y1;
    }

    public void output(PrintWriter printWriter) {
        printWriter.printf("%010d%010d%010d%010d%010d%010d\r\n", color.getRGB(), x1,
                y1, x2, y2);
    }

    public Shape getShape() {
        return shape;
    }
    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }
}
