package com.company.game;

import com.company.tiles.Bag;
import com.company.tiles.Board;
import com.company.tiles.Tile;

import java.util.*;

public class Game {
    private final Bag bag = new Bag();
    private final Board board = new Board();
    private final Dictionary dictionary = new Dictionary();
    private final List<Player> players = new ArrayList<>();
    private Map<Player, Integer> scoreTable = new HashMap<>();
    private int turn = 1;
    private int numberOfPlayers = 0;
    private boolean active = true;

    public void addPlayer(Player player) {
        players.add(player);
        scoreTable.put(player, 0);
        player.setGame(this);
        numberOfPlayers++;
        player.setTurn(numberOfPlayers);
        player.setBag(bag);
    }

    public void removePlayer(Player player){
        players.remove(player);
    }

    public void play() {

        for (Player player : players) new Thread(player).start();

        TimeKeeper daemon = new TimeKeeper();
        daemon.setGame(this);
        daemon.setTimeLimit(60);
        new Thread(daemon).start();
    }

    public void endGame(){
        active = false;
        for (Player player : players) player.setRunning(false);
        while(players.size() != 0){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(board);
        System.out.println(scoreTable);
        System.out.println("The winner is: " +
                scoreTable.entrySet().stream()
                        .max((player1, player2) -> player1.getValue() > player2.getValue() ? 1 : -1)
                        .get()
                        .getKey()
                );
    }

    public static void main(String args[]) {
        Game game = new Game();
        game.addPlayer(new Player("Player 1"));
        game.addPlayer(new Player("Player 2"));
        game.addPlayer(new Player("Player 3"));
        game.play();
    }

    public synchronized int getTurn(){
        return turn;
    }

    public synchronized void updateTurn(){
        turn = (turn % numberOfPlayers) + 1;
    }

    public Bag getBag() {
        return bag;
    }

    public Board getBoard() {
        return board;
    }

    public Dictionary getDictionary() { return dictionary; }

    public void addScore(Player player, Integer wordScore) {

        Integer newScore = scoreTable.get(player) + wordScore;
        scoreTable.put(player, newScore);

    }

    public boolean isActive(){
        return active;
    }
}
