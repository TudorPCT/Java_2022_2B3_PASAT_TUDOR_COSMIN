package com.company.catalog;

import com.company.item.Item;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import com.company.catalog.InvalidCatalogException;
public class CatalogUtil {

    public static void add(Catalog catalog){
        String id;
        String title;
        String location;
        String tagName;
        Map<String, Object> tags = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter id: ");
        id = scanner.nextLine();
        System.out.print("Enter title: ");
        title = scanner.nextLine();
        System.out.print("Enter location: ");
        location = scanner.nextLine();

        System.out.print("Enter tags (enter \'end\' to stop): \nTag Name: ");
        while(!(tagName = scanner.nextLine()).equals("end")) {
            System.out.print("Tag Content: ");
            tags.put(tagName, scanner.nextLine());
            System.out.print("Tag Name: ");
        }
        Item newItem = new Item(id, title, location, tags);


        catalog.add(newItem);
        scanner.close();
    }

    public static String toString(Catalog catalog) {

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

    public static void save(Catalog catalog, String path) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File(path), catalog);
    }

    public static Catalog load(String path) throws InvalidCatalogException{

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(path), Catalog.class);
        } catch (Exception e){
            throw new InvalidCatalogException(e);
        }

    }

}
