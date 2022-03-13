package com.company;

public class Computer extends Node implements Identifiable,Storage{
    private String address;
    private int storageCapacity;

    Computer(String name, String location,  String newAddress, int newCapacity){
        super(name,location);
        address = newAddress;
        storageCapacity = newCapacity;
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
    public int getStorageCapacity() {
        return storageCapacity;
    }

    @Override
    public void setStorageCapacity(int newStorage) {
        storageCapacity = newStorage;
    }

    @Override
    public String toString() {
        return "Computer{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", storageCapacity=" + storageCapacity +
                '}';
    }
}
