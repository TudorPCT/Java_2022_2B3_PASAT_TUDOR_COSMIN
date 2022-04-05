package com.company.app;

import java.io.Serializable;

public class Node implements Serializable {
    private int row;
    private int column;
    private boolean chosen;
    private int color;

    public Node(int row, int column){
        this.row = row;
        this.column = column;
        chosen = false;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void chooseNode() {
        this.chosen = true;
    }

    public boolean isChosen() {
        return chosen;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    @Override
    public String toString() {
        return "Node{" +
                "row=" + row +
                ", column=" + column +
                '}';
    }
}
