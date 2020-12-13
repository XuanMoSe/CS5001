package listener;

import util.SetOfConstant;
import util.ToolsFunction;
import view.ViewWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * button listen accept command for UI button
 */
public class BtnListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if ("Draw" == e.getActionCommand()) {
            ViewWindow.mode = SetOfConstant.DRAW_MODE;
            ViewWindow.panelDisplayArea.setSelectedShape(null);
            ViewWindow.panelDisplayArea.repaint();
        } else if (e.getActionCommand().equals("Select")) {
            ViewWindow.mode = SetOfConstant.SELECT_MODE;
        } else if (e.getActionCommand().equals("Undo")) {
            ViewWindow.panelDisplayArea.setSelectedShape(null);
            ToolsFunction.undo();
        } else if (e.getActionCommand().equals("Redo")) {
            ViewWindow.panelDisplayArea.setSelectedShape(null);
            ToolsFunction.redo();
        }
    }
}
