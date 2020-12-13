package util;

import fileoperate.SaveFile;
import model.Shapes;
import view.ViewWindow;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class ToolsFunction {

    public static int windowExit() {
        if (SaveFile.hasSave == false) {
            Object[] options = { "Save", "Not" };
            int num;
            num = JOptionPane.showOptionDialog(null, "Save Change Or Not?", "Warning", JOptionPane.DEFAULT_OPTION,
                    JOptionPane.WARNING_MESSAGE, null, options, options[0]);
            if (num == 0) {
                SaveFile savefile = new SaveFile();
                System.exit(0);
            } else if (num == 1) {
                System.exit(0);
            }
        } else {
            System.exit(0);
        }
        return 0;
    }

    public static void undo() {
        if (ViewWindow.undoStack.size() > 0) {
            try {
                /*put the current list on the redoStack*/
                ViewWindow.redoStack.push(serialize(ViewWindow.shapeList));
                /*make the last item of the undoStack the list*/
                ViewWindow.shapeList = new ArrayList<Shapes>(deserialize((ViewWindow.undoStack.pop())));
                ViewWindow.panelDisplayArea.repaint();
            }
            catch (ClassNotFoundException e) {
            }
            catch (IOException e) {
            }
        }
    }

    public static void redo() {
        if (ViewWindow.redoStack.size() > 0) {
            try {
                ViewWindow.undoStack.push(serialize(ViewWindow.shapeList));
                ViewWindow.shapeList = deserialize(ViewWindow.redoStack.pop());
                ViewWindow.panelDisplayArea.repaint();
            }
            catch (ClassNotFoundException e) {
            }
            catch (IOException e) {
            }
        }
    }

    public static void addUndoAction() {
        try {
            /*push current state to the undo stack*/
            ViewWindow.redoStack.clear();
            ViewWindow.undoStack.push(serialize(ViewWindow.shapeList));
        }
        catch (IOException e) {
        }
    }

    public static Shapes getShapeAtPos(int x, int y) {
        Shapes selectedXShape = null;
        for (int i = 0; i < ViewWindow.shapeList.size(); i++) {
            if (ViewWindow.shapeList.get(i).isClicked(x, y)) {
                selectedXShape = ViewWindow.shapeList.get(i);
            }
        }
        return selectedXShape;
    }

    public static void addShape(Shapes shapes) {
        addUndoAction();
        ViewWindow.shapeList.add(shapes);
    }

    public static void removeShape(Shapes shapes) {
        addUndoAction();
        ViewWindow.shapeList.remove(shapes);
    }

    public static void clearShapeList() {
        addUndoAction();
        ViewWindow.shapeList.clear();
    }

    public static void setColor(Shapes shapes, Color color) {
        if (ViewWindow.shapeList.contains(shapes)) {
            addUndoAction();
            shapes.setColor(color);
            ViewWindow.panelDisplayArea.repaint();
        }
    }

    public static void clearUndoRedo() {
        ViewWindow.undoStack.clear();
        ViewWindow.redoStack.clear();
    }

    public static void readFromFile(String filename) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(filename);
        ObjectInputStream ois = new ObjectInputStream(fis);
        ArrayList<Shapes> readList = (ArrayList<Shapes>) ois.readObject();
        ViewWindow.shapeList = readList;
        ois.close();
        fis.close();
    }

    public static void saveToFile(String filename) throws IOException {
        FileOutputStream fos = new FileOutputStream(filename);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(ViewWindow.shapeList);
        oos.close();
        fos.close();
    }

    private static byte[] serialize(ArrayList<Shapes> shapeList) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(shapeList);
        os.close();
        out.close();
        return out.toByteArray();
    }

    private static ArrayList<Shapes> deserialize(byte[] serializedShapeList) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(serializedShapeList);
        ObjectInputStream is = new ObjectInputStream(in);
        is.close();
        in.close();
        return (ArrayList<Shapes>) is.readObject();
    }
}
