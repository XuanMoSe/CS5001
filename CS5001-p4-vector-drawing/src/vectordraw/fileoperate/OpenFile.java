package fileoperate;

import model.StraightLines;
import view.ViewWindow;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * open file function but not working very good, so already not use.
 */
public class OpenFile {
    FileReader fileReader;
    JFileChooser jFileChooser;

    public OpenFile() {
        // TODO Auto-generated constructor stub
        jFileChooser = new JFileChooser();
        jFileChooser.setCurrentDirectory(new File("C:\\Users\\xuanm\\Desktop"));
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
        jFileChooser.showOpenDialog(null);
        ViewWindow.shapeList.clear();
        try {
            fileReader = new FileReader(jFileChooser.getSelectedFile());
            while (fileReader.ready()) {
                switch (fileReader.read()) {
                    case 'C':
//                        ViewWindow.shapeList.add(new Circle(fileReader));
                        break;
                    case 'L':
                        ViewWindow.shapeList.add(new StraightLines(fileReader));
                        break;
                    case 'R':
//                        DrawShape.shapes.add(new Rectangle(fileReader));
                        break;
                    default:
                        break;
                }
            }
            fileReader.close();
            JOptionPane.showMessageDialog(null, "Open Success", "Message", 1);
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
            // TODO Auto-generated catch block
            //
            e.printStackTrace();
        }
    }

}
