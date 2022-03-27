package com.company.item;

import com.company.catalog.Catalog;

import java.util.Map;

public class Article extends Item {
    public Article(String newId, String newTitle, String newLocation, Map<String, Object> newMap){
        super(newId,newTitle,newLocation,newMap);
    }
}
