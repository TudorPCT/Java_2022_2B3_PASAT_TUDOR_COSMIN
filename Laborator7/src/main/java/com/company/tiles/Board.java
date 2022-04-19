package com.company.tiles;

import com.company.game.Game;
import com.company.game.Player;

import java.util.*;

public class Board {

    private List<String> words = new ArrayList<>();

    public synchronized void addWord(Player player, String word) {
        words.add(word);
    }

    @Override
    public synchronized String toString() {
        return words.toString();
    }

}
