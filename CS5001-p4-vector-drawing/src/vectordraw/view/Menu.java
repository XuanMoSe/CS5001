package view;

import listener.MenuListener;
import javax.swing.*;
import java.awt.event.KeyEvent;

/**
 * menu bar
 */
public class Menu extends JMenuBar {
    /**
     * set menu
     */
    JMenu jMenu_File;
    JMenu jMenu_Edit;
    JMenu jMenu_Help;
    /**
     * set menu content
     */
    JMenuItem jMenuItem_new;
    JMenuItem jMenuItem_open;
    JMenuItem jMenuItem_save;

    JMenuItem jMenuItem_fill;
    JMenuItem jMenuItem_undo;
    JMenuItem jMenuItem_redo;
    JMenuItem jMenuItem_clear;

    JMenuItem jMenuItem_help;
    JMenuItem jMenuItem_about;
    public Menu() {
        jMenu_File = new JMenu("File");
        jMenu_File.setMnemonic(KeyEvent.VK_F);

        jMenu_Edit = new JMenu("Edit");
        jMenu_File.setMnemonic(KeyEvent.VK_E);

        jMenu_Help = new JMenu("Help");
        jMenu_File.setMnemonic(KeyEvent.VK_H);

        jMenuItem_new = new JMenuItem("New");
        jMenuItem_open = new JMenuItem("Open");
        jMenuItem_save = new JMenuItem("Save");

        jMenuItem_fill = new JMenuItem("Fill");
        jMenuItem_undo = new JMenuItem("Undo");
        jMenuItem_redo = new JMenuItem("Redo");
        jMenuItem_clear = new JMenuItem("Clear");

        jMenuItem_help = new JMenuItem("Help");
        jMenuItem_about = new JMenuItem("About");

        add(jMenu_File);
        add(jMenu_Edit);
        add(jMenu_Help);

        jMenu_File.add(jMenuItem_new);
        jMenu_File.addSeparator();
        jMenu_File.add(jMenuItem_open);
        jMenu_File.add(jMenuItem_save);

        jMenu_Edit.add(jMenuItem_fill);
        jMenu_Edit.add(jMenuItem_undo);
        jMenu_Edit.add(jMenuItem_redo);
        jMenu_Edit.add(jMenuItem_clear);

        jMenu_Help.add(jMenuItem_help);
        jMenu_Help.add(jMenuItem_about);

        jMenuItem_new.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_N));
        jMenuItem_open.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_O));
        jMenuItem_save.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_S));

        jMenuItem_fill.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_F));
        jMenuItem_redo.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_R));
        jMenuItem_undo.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_U));
        jMenuItem_clear.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_C));

        jMenuItem_help.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_H));
        jMenuItem_about.setAccelerator(KeyStroke.getKeyStroke((char) KeyEvent.VK_A));

        MenuListener menuListener=new MenuListener();

        jMenuItem_new.addActionListener(menuListener);
        jMenuItem_open.addActionListener(menuListener);
        jMenuItem_save.addActionListener(menuListener);

        jMenuItem_fill.addActionListener(menuListener);
        jMenuItem_redo.addActionListener(menuListener);
        jMenuItem_undo.addActionListener(menuListener);
        jMenuItem_clear.addActionListener(menuListener);

        jMenuItem_help.addActionListener(menuListener);
        jMenuItem_about.addActionListener(menuListener);
    }
}
