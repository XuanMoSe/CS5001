package view;

import listener.BtnListener;
import listener.ColorHandler;
import listener.RadioListener;
import listener.WindowClose;
import util.SetOfConstant;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Stack;
import model.Shapes;

public class ViewWindow extends JFrame {
    /*right side drawing area*/
    public static DrawPanel panelDisplayArea = new DrawPanel();
    /*choose color*/
    public static ColorHandler colorHandler = new ColorHandler();
    /*left command area*/
    JPanel panelCommandArea = new JPanel(new FlowLayout());
    /*choose shape type*/
    JPanel panelShape = new JPanel(new GridLayout(5, 2));
    /*choose function*/
    JPanel panelFun = new JPanel(new GridLayout(2, 2));
    Menu menu = new Menu();

    static JButton btnDrawMode = new JButton("Draw");
    static JButton btnSelectMode = new JButton("Select");
    static JButton btnUndo = new JButton("Undo");
    static JButton btnRedo = new JButton("Redo");

    public static JRadioButton btnLine = new JRadioButton("Straight Line", true);
    public static JRadioButton btnRec = new JRadioButton("Rectangle");
    public static JRadioButton btnEllipse = new JRadioButton("Ellipse");
    public static JRadioButton btnStar = new JRadioButton("Star");

    public static JRadioButton btnSquare = new JRadioButton("Square");
    public static JRadioButton btnCircle = new JRadioButton("Circle");

    public static JRadioButton btnTri = new JRadioButton("Triangles");
    public static JRadioButton btnPara = new JRadioButton("Parallelograms");
    public static JRadioButton btnHex = new JRadioButton("Hexagons");

    ButtonGroup grpShape = new ButtonGroup();

    public static ArrayList<Shapes> shapeList = new ArrayList<Shapes>();
    public static Stack<byte[]> redoStack = new Stack<byte[]>();
    public static Stack<byte[]> undoStack = new Stack<byte[]>();

    public static int shapeMode = SetOfConstant.LINE_MODE;
    public static int mode = SetOfConstant.DRAW_MODE;

    public ViewWindow() {
        setTitle("Vector Drawing");
        setSize(850, 680);
        setLayout(new FlowLayout());
        panelCommandArea.setBorder(new TitledBorder("Commend"));
        Dimension dimension = new Dimension(200, 600);
        panelCommandArea.setPreferredSize(dimension);
        panelCommandArea.setLayout(new GridLayout(3, 1));

        setJMenuBar(menu);
        add(panelCommandArea);
        add(panelDisplayArea);

        panelShape.setPreferredSize(new Dimension(190, 140));
        panelFun.setPreferredSize(new Dimension(190, 140));

        panelShape.setBackground(Color.red);
        panelFun.setBackground(Color.green);

        panelShape.setBorder(new TitledBorder("Shape"));
        panelFun.setBorder(new TitledBorder("Function"));

        panelCommandArea.add(panelShape);
        panelCommandArea.add(colorHandler);
        panelCommandArea.add(panelFun);

        panelShape.add(btnLine, 0);
        panelShape.add(btnRec, 1);
        panelShape.add(btnEllipse, 2);
        panelShape.add(btnStar, 3);
        panelShape.add(btnSquare, 4);
        panelShape.add(btnCircle, 5);
        panelShape.add(btnTri, 6);
        panelShape.add(btnPara, 7);
        panelShape.add(btnHex, 8);

        grpShape.add(btnLine);
        grpShape.add(btnRec);
        grpShape.add(btnEllipse);
        grpShape.add(btnStar);
        grpShape.add(btnSquare);
        grpShape.add(btnCircle);
        grpShape.add(btnTri);
        grpShape.add(btnPara);
        grpShape.add(btnHex);

        RadioListener radioListener = new RadioListener();
        btnLine.addItemListener(radioListener);
        btnRec.addItemListener(radioListener);
        btnEllipse.addItemListener(radioListener);
        btnStar.addItemListener(radioListener);
        btnSquare.addItemListener(radioListener);
        btnCircle.addItemListener(radioListener);
        btnTri.addItemListener(radioListener);
        btnPara.addItemListener(radioListener);
        btnHex.addItemListener(radioListener);

        btnDrawMode.setMnemonic(KeyEvent.VK_D);
        btnSelectMode.setMnemonic(KeyEvent.VK_L);
        btnUndo.setMnemonic(KeyEvent.VK_U);
        btnRedo.setMnemonic(KeyEvent.VK_R);

        BtnListener btnListener = new BtnListener();
        btnDrawMode.addActionListener(btnListener);
        btnSelectMode.addActionListener(btnListener);
        btnUndo.addActionListener(btnListener);
        btnRedo.addActionListener(btnListener);

        panelFun.add(btnDrawMode, 0);
        panelFun.add(btnSelectMode, 1);
        panelFun.add(btnUndo, 2);
        panelFun.add(btnRedo, 3);

        WindowClose windowClose = new WindowClose();
        addWindowListener(windowClose);
        setVisible(true);
    }
}
