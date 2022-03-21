package com.company;

import java.util.Map;

public class Switch extends Node{
    Switch(String newName, String location, Map<Node, Integer> newCost){
        super(newName, location,newCost);
    }

    @Override
    public String toString() {
        return "Switch{" +
                "name='" + name + '\'' +
                '}' + '\n';
    }
}
