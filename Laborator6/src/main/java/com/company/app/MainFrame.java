package com.company.app;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;

public class MainFrame extends JFrame implements Serializable {
    ConfigPanel configPanel;
    ControlPanel controlPanel;
    DrawingPanel canvas;
    GameLogic game;

    public MainFrame(){
        super("My Drawing Application");
        init();
    }

    private void init(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        configPanel = new ConfigPanel(this);
        game = new GameLogic(configPanel.getRows(), configPanel.getCols());
        canvas = new DrawingPanel(this);
        controlPanel = new ControlPanel(this);
        add(configPanel, BorderLayout.PAGE_START);
        add(canvas, BorderLayout.CENTER);
        add(controlPanel,BorderLayout.PAGE_END);

        pack();
    }

}
