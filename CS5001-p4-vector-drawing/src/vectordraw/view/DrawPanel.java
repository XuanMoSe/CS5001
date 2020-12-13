package view;

import model.*;
import util.SetOfConstant;
import util.ToolsFunction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;

public class DrawPanel extends JPanel implements MouseListener, MouseMotionListener {
    /* x position when mouse press */
    private int pressX = 0;
    /* y position when mouse press */
    private int pressY = 0;

    private Shapes drawShape;

    private Shapes selectedShape;

    private Color color;

    private boolean resize = false;

    private boolean drag = false;

    private boolean export = false;

    private boolean setFill = false;

    private int hitResizedBox = SetOfConstant.NOT_HIT;

    private Rectangle2D.Double[] resizeBoxes;

    public DrawPanel() {
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.WHITE);

        resizeBoxes = new Rectangle2D.Double[SetOfConstant.BOX_INT];
        setVisible(true);
        color = Color.BLACK;

        addMouseListener(this);
        addMouseMotionListener(this);
    }

    @Override
    public void paintComponent(Graphics graphics) {
        Graphics2D g = (Graphics2D) graphics;
        super.paintComponent(g);

        /*paint the shapes from the list*/
        for (int i = 0; i < ViewWindow.shapeList.size(); i++) {
            Shapes shapes = ViewWindow.shapeList.get(i);
            shapes.paint(g);
        }

        /*paint the shape that is currently being drawn*/
        if ((drawShape != null) && !drawShape.equals(null)) {
            g.setColor(color);
            g.draw(drawShape.getShape());
        }

        /*add the selection marks if applicable*/
        if (!export && (resizeBoxes != null) && !resizeBoxes.equals(null) && (selectedShape != null)
                && !selectedShape.equals(null)) {
            for (int i = 0; i < resizeBoxes.length; i++) {
                if ((resizeBoxes[i] != null) && !resizeBoxes[i].equals(null)) {
                    g.setColor(Color.BLACK);
                    g.draw(resizeBoxes[i]);
                }
            }
        }
    }

    Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Shapes getSelectedShapes() {
        return selectedShape;
    }

    public void setSelectedShape(Shapes selectedShape) {
        this.selectedShape = selectedShape;
    }

    private int getResizeBoxNumber(int x, int y) {
        if ((resizeBoxes != null) && !resizeBoxes.equals(null)) {
            for (int i = 0; i < resizeBoxes.length; i++) {
                if ((resizeBoxes[i] != null) && !resizeBoxes[i].equals(null)) {
                    if (resizeBoxes[i].contains(x, y)) {
                        return i;
                    }
                }
            }
        }
        return SetOfConstant.NOT_HIT;
    }

    public Rectangle2D.Double[] getSelections(Shapes shapes) {

        Rectangle2D.Double[] selections = new Rectangle2D.Double[SetOfConstant.BOX_INT];
        if ((shapes != null) && !shapes.equals(null)) {

            selections[SetOfConstant.UPPER_LEFT_CORNER] = new Rectangle2D.Double(shapes.getShape().getBounds().getMinX() - SetOfConstant.BOX_SIZE,
                    shapes.getShape().getBounds().getMinY() - SetOfConstant.BOX_SIZE, 2 * SetOfConstant.BOX_SIZE, 2 * SetOfConstant.BOX_SIZE);
            selections[SetOfConstant.UPPER_RIGHT_CORNER] = new Rectangle2D.Double(shapes.getShape().getBounds().getMaxX() - SetOfConstant.BOX_SIZE,
                    shapes.getShape().getBounds().getMinY() - SetOfConstant.BOX_SIZE, 2 * SetOfConstant.BOX_SIZE, 2 * SetOfConstant.BOX_SIZE);
            selections[SetOfConstant.BOTTOM_LEFT_CORNER] = new Rectangle2D.Double(
                    shapes.getShape().getBounds().getMinX() - SetOfConstant.BOX_SIZE,
                    shapes.getShape().getBounds().getMaxY() - SetOfConstant.BOX_SIZE, 2 * SetOfConstant.BOX_SIZE, 2 * SetOfConstant.BOX_SIZE);
            selections[SetOfConstant.BOTTOM_RIGHT_CORNER] = new Rectangle2D.Double(
                    shapes.getShape().getBounds().getMaxX() - SetOfConstant.BOX_SIZE,
                    shapes.getShape().getBounds().getMaxY() - SetOfConstant.BOX_SIZE, 2 * SetOfConstant.BOX_SIZE, 2 * SetOfConstant.BOX_SIZE);

            return selections;
        }

        else {
            return null;
        }
    }

    public boolean isSetFill() {
        return setFill;
    }

    public void setSetFill(boolean setFill) {
        this.setFill = setFill;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        /*reset selection at mouse click*/
        selectedShape = null;
        /*when mouse is clicked in select mode, go through the list of shapes and get a shape if one was hit*/
        if (ViewWindow.mode == SetOfConstant.SELECT_MODE) {
            selectedShape = ToolsFunction.getShapeAtPos(e.getX(), e.getY());
            /*show the resize boxes of the selected shape*/
            if ((selectedShape != null) && !selectedShape.equals(null)) {
                resizeBoxes = getSelections(selectedShape);
            }
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressX = e.getX();
        pressY = e.getY();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // stop dragging when released
        drag = false;
        // stop resizing when released
        resize = false;
        hitResizedBox = SetOfConstant.NOT_HIT;

        // reset selection boxes for the selected element as soon as the mouse was released
        if (ViewWindow.mode == SetOfConstant.SELECT_MODE) {
            if ((selectedShape != null) && !selectedShape.equals(null)) {
                selectedShape.updateBounds();
                resizeBoxes = getSelections(selectedShape);
            }
        }

        // if in draw mode and a new shape was drawn, add it to the list
        else if (ViewWindow.mode == SetOfConstant.DRAW_MODE) {
            if ((drawShape != null) && !drawShape.equals(null)) {
                ToolsFunction.addShape(drawShape);
            }
            drawShape = null;
        }
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // preview of the shape that is being drawn when in draw mode
        if (ViewWindow.mode == SetOfConstant.DRAW_MODE) {
            switch (ViewWindow.shapeMode) {
                case SetOfConstant.LINE_MODE:
                    drawShape = new StraightLines();
                    drawShape.setColor(color);
                    drawShape.setFill(setFill);
                    drawShape.construct(pressX, pressY, e.getX(), e.getY());
                    break;
                case SetOfConstant.RECTANGLE_MODE:
                    drawShape = new Rectangles();
                    drawShape.setColor(color);
                    drawShape.setFill(setFill);
                    drawShape.construct(pressX, pressY, e.getX(), e.getY());
                    break;
                case SetOfConstant.ELLIPSE_MODE:
                    drawShape = new Ellipses();
                    drawShape.setColor(color);
                    drawShape.setFill(setFill);
                    drawShape.construct(pressX, pressY, e.getX(), e.getY());
                    break;
                case SetOfConstant.STAR_MODE:
                    drawShape = new FivePointedStars();
                    drawShape.setColor(color);
                    drawShape.setFill(setFill);
                    drawShape.construct(pressX, pressY, e.getX(), e.getY());
                    break;
                case SetOfConstant.SQUARE_MODE:
                    drawShape = new Squares();
                    drawShape.setColor(color);
                    drawShape.setFill(setFill);
                    drawShape.construct(pressX, pressY, e.getX(), e.getY());
                    break;
                case SetOfConstant.CIRCLE_MODE:
                    drawShape = new Circles();
                    drawShape.setColor(color);
                    drawShape.setFill(setFill);
                    drawShape.construct(pressX, pressY, e.getX(), e.getY());
                    break;
                case SetOfConstant.TRIANGLE_MODE:
                    drawShape = new Triangles();
                    drawShape.setColor(color);
                    drawShape.setFill(setFill);
                    drawShape.construct(pressX, pressY, e.getX(), e.getY());
                    break;
                case SetOfConstant.PARALLELOGRAM_MODE:
                    drawShape = new Parallelograms();
                    drawShape.setColor(color);
                    drawShape.setFill(setFill);
                    drawShape.construct(pressX, pressY, e.getX(), e.getY());
                    break;
                case SetOfConstant.HEXAGON_MODE:
                    drawShape = new Hexagons();
                    drawShape.setColor(color);
                    drawShape.setFill(setFill);
                    drawShape.construct(pressX, pressY, e.getX(), e.getY());
                    break;

                default:
                    break;
            }
        }

        // in select mode check if one of the resize boxes was hit and resize accordingly
        // if not check if the mouse was dragged on the element and drag it accordingly
        else if ((ViewWindow.mode == SetOfConstant.SELECT_MODE) && (selectedShape != null) && !selectedShape.equals(null)) {

            int tempHitSelection = getResizeBoxNumber(e.getX(), e.getY());

            // if one of the boxes was dragged, start the resize mode
            if (!resize && (tempHitSelection >= 0) && (tempHitSelection < SetOfConstant.NOT_HIT)) {
                ToolsFunction.addUndoAction();
                hitResizedBox = tempHitSelection;
                resize = true;
            }

            // execute the resize mode as long as the mouse is pressed
            else if (resize) {
                resizeBoxes = getSelections(selectedShape);
                selectedShape.resize(e.getX(), e.getY(), hitResizedBox);
            }

            // check if the shape was clicked and initiate the drag mode if so
            else if (selectedShape.isClicked(e.getX(), e.getY()) && !drag) {
                ToolsFunction.addUndoAction();
                drag = true;
            }

            // execute the drag mode as long as the mouse is clicked
            else if (drag) {
                selectedShape.dragTo(e.getX(), e.getY());
                resizeBoxes = getSelections(selectedShape);
            }
        }
        repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }


}
