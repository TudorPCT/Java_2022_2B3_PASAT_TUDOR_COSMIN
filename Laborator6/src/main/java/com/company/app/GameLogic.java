package com.company.app;

import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameLogic implements Serializable {
    List<Node> nodes;
    Map<Integer, List<Node>> adjacencyList;
    Node lastNode;
    Integer player;
    int rows,cols;

    public GameLogic(int nrRows, int nrCols){
        this.init(nrRows,nrCols);
    }

    public void init(int nrRows, int nrCols){
        this.rows = nrRows;
        this.cols = nrCols;
        player = 1;
        lastNode = null;
        nodes = new ArrayList<>();
        adjacencyList = new HashMap<>();
        for(int row = 0; row < rows; row++){
            for(int column = 0; column < cols; column++){
                nodes.add(new Node(row,column));
                adjacencyList.put(row * cols + column, new ArrayList<>());
            }
        }
    }


    public void addLine(int x, int y, int x1, int y1) {
        adjacencyList.get(x * cols + y).add(nodes.get(x1 * cols + y1));
        adjacencyList.get(x1 * cols + y1).add(nodes.get(x * cols + y));
    }

    public void print() {
        for(int row = 0; row < rows; row++) {
            for (int column = 0; column < cols; column++)
                System.out.println(nodes.get(row * cols + column) + " " + adjacencyList.get(row * cols + column));
        }
    }

    public boolean validate(int row, int column) {
        if(lastNode == null || (!nodes.get(row * cols + column).isChosen()
                && adjacencyList.get(lastNode.getRow() * cols + lastNode.getColumn())
                .contains(nodes.get(row * cols + column)))){
            lastNode = nodes.get(row * cols + column);
            lastNode.chooseNode();
            player++;
            return true;
        }
        return false;
    }

    public boolean checkMoveStatus(){
        for(Node node : adjacencyList.get(lastNode.getRow() * cols + lastNode.getColumn()))
            if (!node.isChosen())
                return true;
        return false;
    }

    public String getWinner(){
        if((player = (player + 1) % 2) == 0)
            return new String("Blue Won");
        else
            return new String("Red Won");
    }

    public void setNodeColor(int index, int color) {
        nodes.get(index).setColor(color);
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }
}
