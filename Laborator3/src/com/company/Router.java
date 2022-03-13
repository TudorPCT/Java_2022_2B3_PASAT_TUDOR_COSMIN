package com.company;

public class Router extends Node implements Identifiable{
    private String address;

    public Router(String newName, String location, String newAddress){
        super(newName,location);
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
                ", address='" + address + '\'' +
                '}';
    }
}
