package com.company.app;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;

    public MainFrame(){
        super("My Drawing Application");
        init();
    }

    private void init(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        configPanel = new ConfigPanel(this);
        canvas = new DrawingPanel(this);
        controlPanel = new ControlPanel(this);

        add(configPanel, BorderLayout.PAGE_START);
        add(canvas, BorderLayout.CENTER);
        add(controlPanel,BorderLayout.PAGE_END);

        pack();
    }

}
