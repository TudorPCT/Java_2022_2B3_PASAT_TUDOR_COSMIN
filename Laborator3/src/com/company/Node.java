package com.company;

import java.util.HashMap;
import java.util.Map;

public abstract class Node{
    protected String name;
    protected String location;
    protected Map<Node, Integer> cost = new HashMap<>();

    public Node(String newName, String newLocation){
        this.name = newName;
        this.location = newLocation;
    }
    public void setCost(Node node, int value) { cost.put(node, value);}

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Map<Node, Integer> getCost() {
        return cost;
    }

    public abstract String toString();
}
