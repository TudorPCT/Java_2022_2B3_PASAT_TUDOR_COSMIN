package com.company;

public class Room {
    private String name;
    private Types type;
    private int capacity;

    public Room() { }

    public Room(String newName, Types newType, int newCapacity) {
        this.name = newName;
        this.type = newType;
        this.capacity = newCapacity;
    }

    public Types getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", capacity=" + capacity +
                '}';
    }
}
