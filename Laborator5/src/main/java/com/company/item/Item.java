package com.company.item;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


public class Item implements Serializable {
    protected String id;
    protected String title;
    protected String location;
    private Map<String, Object> tags;

    public Item(){
        tags = new HashMap<>();
    }
    public Item(String newId, String newTitle, String newLocation, Map<String, Object> newMap) {
        id = newId;
        title = newTitle;
        location = newLocation;
        tags = newMap;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void addTag(String key, Object obj) {
        tags.put(key, obj);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public Map<String, Object> getTags() {
        return tags;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Item{" + "id='")
                .append(id).append('\'')
                .append(", title='")
                .append(title).append('\'')
                .append(", location='")
                .append(location)
                .append('\'');
        if(tags != null)
                stringBuilder.append(", tags=").append(tags);
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
