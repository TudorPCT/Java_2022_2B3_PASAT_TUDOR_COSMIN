package com.company;

import java.util.Map;

public class Router extends Node implements Identifiable{
    private String address;

    public Router(String newName, String location, String newAddress, Map<Node, Integer> newCost){
        super(newName,location,newCost);
        address = newAddress;
    }
    @Override
    public String getAddress() {
        return address;
    }

    @Override
    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Router{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", address='" + address + '\'' +
                '}' + '\n';
    }
}
