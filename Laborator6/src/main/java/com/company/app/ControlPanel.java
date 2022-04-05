package com.company.app;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.RenderedImage;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;

public class ControlPanel extends JPanel implements Serializable {

    final MainFrame frame;
    JButton loadBtn = new JButton("Load");
    JButton saveBtn = new JButton("Save");
    JButton exitBtn = new JButton("Exit");
    JButton screenShotBtn = new JButton("Screen Shot");
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public ControlPanel(MainFrame frame){
        this.frame = frame;
        init();
    }

    private void init(){

        setLayout(new GridLayout(1,4));
        exitBtn.addActionListener(this::exitGame);
        saveBtn.addActionListener(this::saveGame);
        loadBtn.addActionListener(this::loadGame);
        screenShotBtn.addActionListener(this::screenShot);
        add(loadBtn);
        add(saveBtn);
        add(screenShotBtn);
        add(exitBtn);
    }

    private void loadGame(ActionEvent actionEvent) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("./saveFile/save.ser");
            ObjectInputStream in = new ObjectInputStream(fis);
            GameLogic game = (GameLogic) in.readObject();
            drawUpdate(game);
            fis.close();
        } catch (Exception e) {
            frame.canvas.label.setText("Loading is not avalable");
            e.printStackTrace();
        }
    }

    private void drawUpdate(GameLogic game){
        frame.game = game;
        frame.configPanel.setSpinnerRows(game.getRows());
        frame.configPanel.setSpinnerCols(game.getCols());
        frame.canvas.init(game.getRows(), game.getCols());
        frame.canvas.drawBoard(game.nodes, game.adjacencyList);
        frame.canvas.player = game.player;
        frame.repaint();
    }

    private void screenShot(ActionEvent actionEvent) {
        File outputfile = new File("screenShots/GameScreenShot " + sdf.format(Timestamp.from(Instant.now())) + ".png");
        try {
            ImageIO.write(frame.canvas.image, "png", outputfile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveGame(ActionEvent actionEvent){
        FileOutputStream fos;
        try {
            fos = new FileOutputStream("./saveFile/save.ser");
            ObjectOutputStream out = new ObjectOutputStream(fos);
            out.writeObject(frame.game);
            out.flush();
            fos.close();
        }catch (Exception e) {frame.canvas.label.setText("Saving is not avalable");
            e.printStackTrace();}
    }

    private void exitGame(ActionEvent e){
        frame.dispose();
    }

}
