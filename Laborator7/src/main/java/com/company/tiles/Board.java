package com.company.tiles;

import com.company.gameLogic.Player;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private final List<String> words;

    public Board(){
        words = new ArrayList<>();
    }

    public synchronized void addWord(Player player, String word) {
        words.add(word);
        System.out.println(player.getName() + ": " + word);
    }

    @Override
    public synchronized String toString() {
        return words.toString();
    }

}
