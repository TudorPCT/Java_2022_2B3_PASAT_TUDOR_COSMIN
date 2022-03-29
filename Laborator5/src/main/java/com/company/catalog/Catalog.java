package com.company.catalog;

import com.company.item.Item;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class Catalog implements Serializable {
    private String name;
    private List<Item> items;

    public Catalog(){
        items = new ArrayList<>();
    }
    public Catalog(String newName){
        this(newName,new ArrayList<>());
    }

    public Catalog(String newName, List<Item> itemList){
        name = newName;
        items = itemList;
    }

    public void add(Item item) {
        items.add(item);
    }

    public String getName() {
        return name;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Item findById(String id) {
        return items.stream()
                .filter(d -> d.getId()
                    .equals(id))
                        .findFirst()
                            .orElse(null);
    }


}
