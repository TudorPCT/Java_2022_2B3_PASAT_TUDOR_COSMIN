package com.company;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract class Node{
    protected String name;
    protected String location;
    protected Map<Node, Integer> cost = new HashMap<>();

    public Node(String newName, String newLocation, Map<Node, Integer> newCost){
        this.name = newName;
        this.location = newLocation;
        this.cost = newCost;
    }
    public void setCost(Node node, int value) { cost.put(node, value);}

    public void setName(String name) {
        this.name = name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public String getName() {
        return name;
    }

    public Integer getCost(Node node) {
        return cost.get(node);
    }

    public abstract String toString();


}
