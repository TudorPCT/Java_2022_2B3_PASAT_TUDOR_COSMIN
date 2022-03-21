package com.company;

import java.util.Arrays;

public class Street implements Comparable<Street>{
    private String name;
    private int length;
    private Intersection[] joinedIntersections;

    public Street(String newName, int newLength, Intersection[] newJoinedIntersections) {
        name = newName;
        length = newLength;
        joinedIntersections = newJoinedIntersections;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public int getLength() {
        return length;
    }

    @Override
    public int compareTo(Street o) {
        if(this.length < o.length)
            return -1;
        else if(this.length > o.length)
            return 1;
        else
            return 0;
    }

    @Override
    public String toString() {
        return "Street{" +
                "name='" + name + '\'' +
                ", length=" + length +
                ", joinedIntersections=" + Arrays.toString(joinedIntersections) +
                '}' + '\n';
    }

}
