package com.company.tiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bag {

    private final List<Tile> tiles;
    Random rand = new Random();

    public Bag() {
        tiles = new ArrayList<>();
        for (char c = 'a'; c <= 'z'; c++) {
            for(int i = 0 ; i < 10; i++)
                tiles.add(new Tile(c, Math.abs(rand.nextInt() % 10) + 1));
        }
    }

    public synchronized List<Tile> extractTiles(int howMany) {
        List<Tile> extracted = new ArrayList<>();
        int index;
        for (int i = 0; i < howMany; i++) {
            if (tiles.isEmpty()) {
                break;
            }
            index = Math.abs(rand.nextInt() % tiles.size());
            extracted.add(tiles.get(index));
            tiles.remove(index);
        }
        return extracted;
    }

    public List<Tile> getTiles() {
        return tiles;
    }
}
