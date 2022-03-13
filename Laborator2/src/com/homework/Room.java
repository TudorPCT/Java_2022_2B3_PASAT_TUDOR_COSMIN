package com.homework;

import java.util.Objects;

public abstract class Room implements Comparable<Room>{
    protected String name;
    protected int capacity;

    public Room() { }

    public Room(String newName, int newCapacity) {
        this.name = newName;
        this.capacity = newCapacity;
    }

    public String getName() {
        return name;
    }

    public abstract String getType();

    public int getCapacity() {
        return capacity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public abstract String toString();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Room)) return false;
        Room room = (Room) o;
        return getCapacity() == room.getCapacity() && getName().equals(room.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getCapacity());
    }

    @Override
    public int compareTo(Room o) {
        if(this.capacity > o.getCapacity())
            return 1;
        return 0;
    }
}
