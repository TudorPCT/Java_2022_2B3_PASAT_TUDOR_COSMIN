package com.company;

public class Intersection {
    private String name;

    public Intersection(String newName){
        name = newName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Intersection{" +
                "name='" + name + '\'' +
                '}' + '\n';
    }
}
