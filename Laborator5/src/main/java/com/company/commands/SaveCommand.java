package com.company.commands;

import com.company.catalog.Catalog;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class SaveCommand implements Command{
    private String path;
    private Catalog catalog;

    public SaveCommand(Catalog newCatalog, String newPath){
        catalog = newCatalog;
        path = newPath;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @Override
    public void execute() {

        try {
            this.save(catalog, path);
        } catch (Exception e){
            System.out.println("Unexpected error writing the file!");
            e.printStackTrace();
        }

    }

    private static void save(Catalog catalog, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), catalog);
    }
}
