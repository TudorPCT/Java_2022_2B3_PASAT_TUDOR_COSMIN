package com.company.tiles;

import java.util.Objects;

public class Tile {
    private final char letter;
    private final int points;

    public Tile(char letter, int points){
        this.letter = letter;
        this.points = points;
    }

    public char getLetter() {
        return letter;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tile)) return false;
        Tile tile = (Tile) o;
        return getLetter() == tile.getLetter();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLetter());
    }

    @Override
    public String toString() {
        return "Tile{" +
                letter +
                " - " + points +
                '}';
    }
}