package com.company.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.Serializable;

public class ConfigPanel extends JPanel implements Serializable {

    final MainFrame frame;
    private JLabel label;
    private JSpinner spinnerRows;
    private JSpinner spinnerCols;
    private JButton createButton;
    private int rows = 10, cols = 10;

    public ConfigPanel(MainFrame frame){
        this.frame = frame;
        init();
    }

    private void init(){
        label = new JLabel("Grid size:");
        spinnerRows = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        spinnerCols = new JSpinner(new SpinnerNumberModel(10, 2, 100, 1));
        createButton = new JButton("Create");
        createButton.addActionListener(this::createAction);
        add(label);
        add(spinnerRows);
        add(spinnerCols);
        add(createButton);
    }

    private void createAction(ActionEvent actionEvent) {
        rows = (Integer) spinnerRows.getValue();
        cols = (Integer) spinnerCols.getValue();

        frame.game.init(rows, cols);

        frame.canvas.init(rows, cols);

        frame.canvas.paintRandomLines();

        frame.canvas.repaint();
    }

    public void setSpinnerRows(int rows){
        this.rows = rows;
        spinnerRows.setValue(rows);
    }


    public void setSpinnerCols(int cols) {
        this.cols = cols;
        spinnerCols.setValue(cols);
    }
    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
