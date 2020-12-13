package fileoperate;

import model.Shapes;
import view.ViewWindow;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.*;
/**
 * save file function but not working very good, so already not use.
 */
public class SaveFile {
    FileWriter fileWriter;
    PrintWriter printWriter;
    JFileChooser jFileChooser;
    public static boolean hasSave=true;
    public SaveFile() {
        jFileChooser=new JFileChooser();
        FileFilter filenameFilter=new FileFilter() {
            @Override
            public boolean accept(File f) {
                if (f.isDirectory()) {
                    return false;
                }
                if (f.getName().endsWith("txt")){
                    return true;
                }
                else {
                    return false;
                }
            }
            @Override
            public String getDescription() {
                return null;
            }
        };
        jFileChooser.setFileFilter(filenameFilter);
        jFileChooser.showSaveDialog(null);
        try {
            fileWriter =new FileWriter(jFileChooser.getSelectedFile());
            printWriter = new PrintWriter(fileWriter);
            for (Shapes s : ViewWindow.shapeList)
                s.output(printWriter);
            printWriter.close();
            fileWriter.close();
            hasSave=true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
