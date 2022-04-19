package com.company.tiles;

import java.util.*;

public class Bag {

    private List<Tile> tiles = Collections.synchronizedList(new ArrayList<Tile>());
    Random rand = new Random();

    public Bag() {
        List<Integer> noTiles = new ArrayList<>(Arrays.asList(9,2,2,4,12,2,3,2,9,1,1,4,2,6,8,2,1,6,4,6,4,2,2,1,2,1));
        List<Integer> points = new ArrayList<>(Arrays.asList(1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10));
        for (char c = 'A'; c <= 'Z'; c++) {
            for(int i = 0 ; i < noTiles.get(c-65); i++)
                tiles.add(new Tile(c, points.get(c-65)));
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

    private synchronized int calculatePoints(String str){
        int points = 0;

        for(int i = 0; i < str.length(); i++){
            points += str.charAt(i);
        }

        return points;
    }

    public List<Tile> getTiles() {
        return tiles;
    }

    public List<Tile> resetTiles(List<Tile> extracted) {
        tiles.addAll(extracted);
        return extractTiles(7);
    }
}
