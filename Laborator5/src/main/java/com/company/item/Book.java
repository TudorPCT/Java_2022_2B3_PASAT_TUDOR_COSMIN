package com.company.item;

import java.util.Map;

public class Book extends Item{
    public Book(String newId, String newTitle, String newLocation, Map<String, Object> newMap){
        super(newId,newTitle,newLocation,newMap);
    }
}
