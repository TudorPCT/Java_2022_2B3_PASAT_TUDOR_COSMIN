package com.homework;

public class LectureHall extends Room{
    private boolean hasProjector;

    public LectureHall(String newName, int newCapacity, boolean hasProj)
    {
        super(newName,newCapacity);
        this.hasProjector = hasProj;
    }
    @Override
    public String getType() {
        return new String("Lecture Hall");
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public boolean getHasProjector() {
        return hasProjector;
    }

    @Override
    public String toString() {
        return "LectureHall{" +
                "name='" + name + '\'' +
                ", type='" + getType() + '\'' +
                ", capacity=" + capacity +
                ", hasProjector=" + hasProjector +
                '}';
    }
}
