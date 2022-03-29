package com.company.commands;

import com.company.catalog.Catalog;
import com.company.catalog.CatalogUtil;
import com.company.catalog.InvalidCatalogException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;

public class LoadCommand implements Command{
    private Catalog catalog = null;
    private String path;

    public void setPath(String path) {
        this.path = path;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    @Override
    public void execute() {
        try {
            catalog = load(path);
        } catch (InvalidCatalogException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    public Catalog load(String path) throws InvalidCatalogException {

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(path), Catalog.class);
        } catch (Exception e){
            throw new InvalidCatalogException(e);
        }

    }


}
