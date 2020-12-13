package listener;

import util.ToolsFunction;
import view.ViewWindow;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.io.File;

/**
 * accept all command form menu bar.
 */
public class MenuListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("New")) {
            ToolsFunction.clearUndoRedo();
            ViewWindow.shapeList.clear();
            ViewWindow.panelDisplayArea.setSelectedShape(null);
            ViewWindow.panelDisplayArea.repaint();
        } else if (e.getActionCommand().equals("Open")) {
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(fc);
            if (returnVal == JFileChooser.APPROVE_OPTION) {

                try {
                    File file = fc.getSelectedFile();
                    ToolsFunction.readFromFile(file.toString());
                    ToolsFunction.clearUndoRedo();
                    ViewWindow.panelDisplayArea.setSelectedShape(null);
                    ViewWindow.panelDisplayArea.repaint();
                }
                catch (Exception e1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Could not load file!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getActionCommand().equals("Save")) {
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showSaveDialog(fc);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                try {

                    File file = fc.getSelectedFile();
                    ToolsFunction.saveToFile(file.toString());
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(new JFrame(), "Could not save file!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getActionCommand().equals("Fill")) {
            if(!ViewWindow.panelDisplayArea.isSetFill()) {
                ViewWindow.panelDisplayArea.setSetFill(true);
            }
            else {
            ViewWindow.panelDisplayArea.setSetFill(false);
            }
        } else if (e.getActionCommand().equals("Undo")) {
            ViewWindow.panelDisplayArea.setSelectedShape(null);
            ToolsFunction.undo();
        } else if (e.getActionCommand().equals("Redo")) {
            ViewWindow.panelDisplayArea.setSelectedShape(null);
            ToolsFunction.redo();
        } else if (e.getActionCommand().equals("Clear")) {
            ToolsFunction.clearShapeList();
            ViewWindow.panelDisplayArea.setSelectedShape(null);
            ViewWindow.panelDisplayArea.repaint();
        } else if (e.getActionCommand().equals("Help")) {
            JOptionPane.showMessageDialog(null, "The Same As WINDOWS_DRAW\n Friendly UI");
        } else if (e.getActionCommand().equals("About")) {
            JOptionPane.showMessageDialog(null, "Created by 200009834\n2020/11/20\nVersion:1.0", "About", 1);
        }
    }
}
