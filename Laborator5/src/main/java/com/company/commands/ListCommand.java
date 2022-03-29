package com.company.commands;

import com.company.catalog.Catalog;
import com.company.item.Item;

public class ListCommand implements Command{

    private Catalog catalog;

    public ListCommand(Catalog newCatalog){
        catalog = newCatalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute(){
        System.out.println(this.toString(catalog));
    }

    private String toString(Catalog catalog) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Catalog{" + "Name: '")
                .append(catalog.getName())
                .append('\'')
                .append(", \nItems: \n");

        var items = catalog.getItems();
        for(Item item : items)
            stringBuilder.append(item).append('\n');
        stringBuilder.append('}');
        return stringBuilder.toString();
    }
}
