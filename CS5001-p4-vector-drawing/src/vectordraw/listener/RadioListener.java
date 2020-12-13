package listener;

import util.SetOfConstant;
import view.ViewWindow;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * accept all command form radio button.
 */
public class RadioListener implements ItemListener {
    @Override
    public void itemStateChanged(ItemEvent e) {
        ItemSelectable itemSelectable = e.getItemSelectable();
        if (itemSelectable.equals(ViewWindow.btnLine))
            ViewWindow.shapeMode = SetOfConstant.LINE_MODE;
        else if (itemSelectable.equals(ViewWindow.btnRec))
            ViewWindow.shapeMode = SetOfConstant.RECTANGLE_MODE;
        else if (itemSelectable.equals(ViewWindow.btnEllipse))
            ViewWindow.shapeMode = SetOfConstant.ELLIPSE_MODE;
        else if (itemSelectable.equals(ViewWindow.btnStar))
            ViewWindow.shapeMode = SetOfConstant.STAR_MODE;
        else if (itemSelectable.equals(ViewWindow.btnSquare))
            ViewWindow.shapeMode = SetOfConstant.SQUARE_MODE;
        else if (itemSelectable.equals(ViewWindow.btnCircle))
            ViewWindow.shapeMode = SetOfConstant.CIRCLE_MODE;
        else if (itemSelectable.equals(ViewWindow.btnTri))
            ViewWindow.shapeMode = SetOfConstant.TRIANGLE_MODE;
        else if (itemSelectable.equals(ViewWindow.btnPara))
            ViewWindow.shapeMode = SetOfConstant.PARALLELOGRAM_MODE;
        else if (itemSelectable.equals(ViewWindow.btnHex))
            ViewWindow.shapeMode = SetOfConstant.HEXAGON_MODE;
    }
}
