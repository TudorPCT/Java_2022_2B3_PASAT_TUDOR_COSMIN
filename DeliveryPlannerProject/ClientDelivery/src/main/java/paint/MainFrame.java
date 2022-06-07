package paint;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    DrawingPanel canvas;
    ConfigPanel configPanel;
    java.awt.List list;
    JLabel label;

    public MainFrame() {
        super("Solution");
        init();
    }
    private void init() {
        label = new JLabel("Welcome to DeliveryPlanner!", SwingConstants.CENTER);
        label.setFont(new Font("Verdana", Font.PLAIN, 40));
        label.setForeground(Color.GRAY);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        configPanel = new ConfigPanel(this);
        canvas = new DrawingPanel(this);
        list = new java.awt.List();
//        list.setSize(new Dimension((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth() / 15,
//                (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth()));
        list.setVisible(false);
       // add(canvas, BorderLayout.CENTER);
      //  canvas.setVisible(false);
        add(label, BorderLayout.CENTER);
        add(configPanel,BorderLayout.NORTH);
        add(list, BorderLayout.EAST);
        pack();
    }
}
