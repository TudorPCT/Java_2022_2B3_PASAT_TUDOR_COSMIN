package com.company.game;

import com.company.tiles.Bag;
import com.company.tiles.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Player implements Runnable {

    private final String name;
    private Game game;
    private Bag bag;
    public boolean running = true;
    private final Scanner keyboard = new Scanner(System.in);
    private List<Tile> extracted = new ArrayList<>();
    private List<Tile> usedTiles = new ArrayList<>();
    private int turn;
    private int numberOfNeededTiles = 7;

    public Player(String name) {
        this.name = name;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    private synchronized boolean submitWord() {

        int wordScore = 0;
        boolean isValid = true;

        synchronized (bag.getTiles()) {
            if (!game.isActive())
                return false;
            extracted.addAll( bag.extractTiles(numberOfNeededTiles) );
            while (game.getTurn() != turn) {
                try {
                    bag.getTiles().wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    return false;
                }
            }
            if (extracted.isEmpty()) {
                return false;
            }

            System.out.println(getName());
            System.out.println("Enter the word with the tiles: " + extracted);
            String word = keyboard.nextLine();

            if(word.length() == 0)
                extracted = game.getBag().resetTiles(extracted);
            if (game.getDictionary().isWord(word.toUpperCase(Locale.ROOT))) {
                game.getBoard().addWord(this, word.toUpperCase());
                for (int i = 0; i < word.length(); i++) {
                    Tile auxTile = new Tile(word.toUpperCase(Locale.ROOT).charAt(i), 0);
                    if (extracted.contains(auxTile)) {
                        wordScore += extracted.get(extracted.indexOf(auxTile)).getPoints();
                        extracted.remove(auxTile);
                        usedTiles.add(auxTile);
                    } else {
                        isValid = false;
                        break;
                    }
                }

                if (isValid) {
                    numberOfNeededTiles = word.length();
                    game.addScore(this, wordScore);
                } else {
                    System.out.println("The word contains unavailable tiles");
                    extracted.addAll(usedTiles);
                    usedTiles.clear();
                }
            } else {
                System.out.println("Invalid word");
            }

            game.updateTurn();
            bag.getTiles().notifyAll();
            return true;
        }
    }



    @Override
    public void run() {
        while(running){
            if(!submitWord()) {
                running = false;
            }
        }
        game.removePlayer(this);
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public String getName() {
        return name;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    @Override
    public String toString() {
        return name;
    }
}
