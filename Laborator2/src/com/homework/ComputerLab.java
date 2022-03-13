package com.homework;

public class ComputerLab extends Room{
    String operatingSystem;

    public ComputerLab(String newName, int newCapacity, String newOS)
    {
        super(newName,newCapacity);
        this.operatingSystem = newOS;
    }

    public String getOperatingSystem(){
        return operatingSystem;
    }
    @Override
    public String getType() {
        return "Computer Lab";
    }

    @Override
    public String toString() {
        return "Computer Lab{" +
                "name='" + name + '\'' +
                ", type='" + getType() + '\'' +
                ", capacity=" + capacity + '\'' +
                ", OS='" + operatingSystem + '\'' +
                '}';
    }
}
