package com.company.commands;

import com.company.item.Item;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;

public class ViewCommand implements Command{
    private Desktop desktop = Desktop.getDesktop();
    private Item item;

    public ViewCommand(Item newItem){
        item = newItem;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public void execute(){
        try{
            this.view(item);
        } catch (Exception e){
            System.out.println("Unexpected error opening the item!");
            e.printStackTrace();
        }
    }

    private void view(Item item) throws IOException {
        if(item.getLocation().startsWith("https") || item.getLocation().startsWith("http")){
            desktop.browse(URI.create(item.getLocation()));
        }
        else{
            File file = new File(item.getLocation());
            desktop.open(file);
        }
    }
}
