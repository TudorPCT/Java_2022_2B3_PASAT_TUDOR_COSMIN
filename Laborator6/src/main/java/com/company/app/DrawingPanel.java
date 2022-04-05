package com.company.app;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class DrawingPanel extends JPanel implements Serializable {
    private final MainFrame frame;
    private int rows;
    private int cols;
    Integer player;
    private int canvasWidth = 500, canvasHeight = 500;
    private int boardWidth, boardHeight;
    private int cellWidth, cellHeight;
    private int padX, padY;
    private int stoneSize = 20;
    private boolean mouseListenerIsActive;
    BufferedImage image;
    JLabel label = new JLabel("");
    Graphics2D offscreen;
    private Stroke stroke1 = new BasicStroke(1f);
    private Stroke stroke2 = new BasicStroke(4f);

    public DrawingPanel(MainFrame frame){
        this.frame = frame;
        createOffScreenImage();
        init(frame.configPanel.getRows(), frame.configPanel.getCols());

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(mouseListenerIsActive) {
                    int column = Math.round((float) (e.getX() - padX + stoneSize / 2) / cellWidth);
                    int row = Math.round((float) (e.getY() - padY + stoneSize / 2) / cellHeight);
                    int x = padX + column * cellWidth;
                    int y = padY + row * cellHeight;
                    int index = ((x - padX + stoneSize / 2) / cellWidth) *  cols + ((y - padY + stoneSize / 2) / cellWidth);

                    if (Math.abs(x - e.getX()) < 10 && Math.abs(y - e.getY()) < 10 && frame.game.validate(row, column)) {
                        drawStone(x, y);
                        frame.game.setNodeColor(index, player);
                        frame.game.nodes.get(row * cols + column).setColor(player);
                    }
                    if (!frame.game.checkMoveStatus()) {
                        mouseListenerIsActive = false;
                        drawMessage();
                    }
                    repaint();
                }
            }
        });

        paintRandomLines();
    }

    private void createOffScreenImage(){
        image = new BufferedImage(canvasWidth, canvasHeight, BufferedImage.TYPE_INT_ARGB);
        offscreen = image.createGraphics();
    }

    final public void init(int rows, int cols){
        this.rows = rows;
        this.cols = cols;
        this.padX = stoneSize + 10;
        this.padY = stoneSize + 20;
        this.cellWidth = (canvasWidth - 2 * padX) / (cols - 1);
        this.cellHeight = (canvasHeight - 2 * padY) / (rows - 1);
        this.boardWidth = (cols - 1) * cellWidth;
        this.boardHeight = (rows - 1) * cellHeight;
        this.mouseListenerIsActive = true;
        this.player = 1;

        offscreen.setColor(Color.WHITE);
        offscreen.fillRect(0,0,canvasWidth,canvasHeight);

        label.setText("Red player is first");
        label.setFont(new Font("Serif", Font.PLAIN, 17));
        add(label);

        setPreferredSize(new Dimension(canvasWidth, canvasHeight));

        paintGrid();

    }

    private void drawMessage(){
        label.setText(frame.game.getWinner());
    }

    private void drawStone(int x, int y) {

        if((player = (player + 1) % 2) == 0) {
            frame.canvas.offscreen.setColor(Color.RED);
            frame.canvas.label.setText("Blue player's turn");
        }
        else {
            frame.canvas.offscreen.setColor(Color.BLUE);
            frame.canvas.label.setText("Red player's turn");
        }

        offscreen.fillOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
    }

    @Override
    protected void paintComponent(Graphics graphics){
        graphics.drawImage(image, 0,0, this);
    }

    public void paintGrid() {
        offscreen.setColor(Color.DARK_GRAY);
        offscreen.setStroke(stroke1);

        for (int row = 0; row < rows; row++) {
            int x1 = padX;
            int y1 = padY + row * cellHeight;
            int x2 = padX + boardWidth;
            int y2 = y1;
            offscreen.drawLine(x1, y1, x2, y2);
        }

        for (int col = 0; col < cols; col++) {
            int y1 = padY;
            int x1 = padX + col * cellWidth;
            int y2 = padY + boardHeight;
            int x2 = x1;
            offscreen.drawLine(x1, y1, x2, y2);
        }

        offscreen.setStroke(stroke1);
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;
                offscreen.setColor(Color.LIGHT_GRAY);
                offscreen.drawOval(x - stoneSize / 2, y - stoneSize / 2, stoneSize, stoneSize);
            }
        }
    }

    public void paintRandomLines(){
        Random rand = new Random();
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {

                int x = padX + col * cellWidth;
                int y = padY + row * cellHeight;

                if(col < cols - 1 && rand.nextDouble() > 0.3) {
                    drawLine(x, y, x + cellWidth, y);
                    frame.game.addLine(row, col, row, col + 1);
                }

                if(row < rows - 1 && rand.nextDouble() > 0.3) {
                    drawLine(x, y, x, y + cellHeight);
                    frame.game.addLine(row, col, row + 1, col);
                }
            }
        }
    }

    private void drawLine(int x1, int y1, int x2, int y2){
        offscreen.setColor(Color.BLACK);
        offscreen.setStroke(stroke2);
        offscreen.drawLine(x1, y1, x2, y2);
        offscreen.setStroke(stroke1);
    }

    public void drawBoard(List<Node> nodes, Map<Integer, List<Node>> adjacencyList) {
        for(Node startNode : nodes){

            int x1 = padX + startNode.getColumn() * cellWidth;
            int y1 = padY + startNode.getRow() * cellHeight;

            for(Node endNode : adjacencyList.get(startNode.getRow() * cols + startNode.getColumn())){

                int x2 = padX + endNode.getColumn() * cellWidth;
                int y2 = padY + endNode.getRow() * cellHeight;

                if(x1 > x2 || y1 > y2)
                    continue;

                drawLine(x1,y1,x2,y2);
            }

            if(startNode.isChosen()) {
                player = (startNode.getColor() + 1) % 2;
                drawStone(x1, y1);
            }
        }

    }
}
