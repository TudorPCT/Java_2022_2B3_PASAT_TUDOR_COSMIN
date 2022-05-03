package app.map;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class MainFrame extends JFrame implements Serializable {
    DrawingPanel canvas;
    JScrollPane scrl;

    public MainFrame() {
        super("My Drawing Application");
        init();
    }

    private void init() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        canvas = new DrawingPanel(this);
        scrl = new JScrollPane(canvas);
        setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
        add(scrl, BorderLayout.CENTER);
        pack();
    }

}
