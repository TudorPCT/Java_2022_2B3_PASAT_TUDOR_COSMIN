package com.company.gameLogic;

import com.company.gameLogic.Game;
import com.company.tiles.Tile;

import java.util.List;

public class Player implements Runnable{
    private String name;
    private Game game;
    private boolean running = true;

    public Player(String name) {
        this.name = name;
    }

    private boolean submitWord() {

        List<Tile> extracted = game.getBag().extractTiles(7);

        if (extracted.isEmpty()) {
            return false;
        }

        StringBuilder word = new StringBuilder();

        for(Tile tile : extracted){
            word.append(tile.getLetter());
        }

        game.getBoard().addWord(this, word.toString());

        try {
            Thread.sleep(50);
        }catch (Exception e){
            System.out.println(e);
        }

        return true;
    }

    @Override
    public void run() {
        while(running){
            if(!submitWord()) running = false;
        }
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

}
