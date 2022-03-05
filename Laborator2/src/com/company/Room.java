package com.company;

public class Room {
    private String name;
    private Type type;
    private int capacity;

    public Room() { }

    public Room(String newName, Type newType, int newCapacity) {
        this.name = newName;
        this.type = newType;
        this.capacity = newCapacity;
    }

    public String getName() { return name; }

    public Type getType() {
        return type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setName(String name) { this.name = name; }

    public void setType(Type type) {
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
