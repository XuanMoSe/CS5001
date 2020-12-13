package listener;

import util.ToolsFunction;
import view.ViewWindow;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Make a color UI to change shape color
 */
public class ColorHandler extends JPanel {
    JColorChooser jColorChooser;
    JButton button;
    Color tempColor;
    JPanel jPanel;
    private static Color color = new Color(0, 0, 0);

    public ColorHandler() {
        super(new GridLayout(2, 1));
        jPanel = new JPanel();
        color = new Color(0, 0, 0);
        setBorder(new TitledBorder("Color"));
        jPanel.setBackground(color);
        setPreferredSize(new Dimension(190, 140));
        setBackground(Color.white);
        button = new JButton();
        jColorChooser = new JColorChooser();
        button.setText("Open the Color manager");
        button.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                tempColor = JColorChooser.showDialog(new JFrame(), "ColorChooser", color.BLACK);
                if (tempColor != null) {
                    color = tempColor;
                    ViewWindow.panelDisplayArea.setColor(color);
                }
                if ((ViewWindow.panelDisplayArea.getSelectedShapes() != null) && !ViewWindow.panelDisplayArea.getSelectedShapes().equals(null)) {
                    ToolsFunction.setColor(ViewWindow.panelDisplayArea.getSelectedShapes(), color);
                }
                jPanel.setBackground(color);
            }

        });
        add(button);
        add(jPanel);
    }
}
