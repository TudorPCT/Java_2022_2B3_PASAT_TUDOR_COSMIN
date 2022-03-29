package com.company;

import com.company.catalog.Catalog;
import com.company.catalog.CatalogUtil;
import com.company.catalog.InvalidCatalogException;
import com.company.commands.*;
import com.company.item.Item;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
	    Main lab5 = new Main();
      //  lab5.compulsory();
        lab5.homework();

    }

    public Catalog createCatalog(){
        Catalog catalog = new Catalog("myCatalog");
        Map<String,Object> map = new HashMap<>();

        map.put("year",1967);
        map.put( "author","Donald E. Knuth");
        map.put("type", "book");
        catalog.add( new Item("knuth67","The Art of Computer Programming", "/Users/cosminpasat/Downloads/PLSQL_-_5_Exceptii.pdf", map) );
        map = new HashMap<>();
        map.put("year",2021);
        map.put("author", "James Gosling & others");
        catalog.add( new Item("java17", "The Java Language Specification", "https://docs.oracle.com/javase/specs/jls/se17/html/index.html", map) );

        return catalog;
    }
    public void compulsory(){

        Catalog catalog = createCatalog();
        try{
            CatalogUtil.save(catalog,"/Users/cosminpasat/Desktop/pa/catalog.json");
        } catch (IOException e){
            System.out.println("Unexpected error writing the file!");
            e.printStackTrace();
        }

        CatalogUtil.add(catalog);
        System.out.println(CatalogUtil.toString(catalog));
        try {
            System.out.println(CatalogUtil.toString(CatalogUtil.load("/Users/cosminpasat/Desktop/pa/catalog.json")));
        } catch (InvalidCatalogException e){
            System.out.println(e);
            e.printStackTrace();
        }
    }

    private void homework() {
        Catalog catalog = createCatalog();
        Command comm = new ReportCommand(catalog);
        comm.execute();
    }
}
