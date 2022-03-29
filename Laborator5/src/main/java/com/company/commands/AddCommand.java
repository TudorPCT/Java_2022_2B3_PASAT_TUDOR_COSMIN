package com.company.commands;

import com.company.catalog.Catalog;
import com.company.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddCommand implements Command{

    private Catalog catalog;

    public AddCommand(Catalog newCatalog){
        catalog = newCatalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }

    @Override
    public void execute() {
        this.add(catalog);
    }

    private void add(Catalog catalog){
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
}
