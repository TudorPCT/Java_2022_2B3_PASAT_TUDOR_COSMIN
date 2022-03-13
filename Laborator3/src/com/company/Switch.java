package com.company;

public class Switch extends Node{
    Switch(String newName, String location){
        super(newName, location);
    }

    @Override
    public String toString() {
        return "Switch{" +
                "name='" + name + '\'' +
                '}';
    }
}
