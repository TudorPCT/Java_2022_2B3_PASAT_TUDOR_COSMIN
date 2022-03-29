package com.company;

import java.util.*;

public class Algorithm {

    public List<Street> kruskal(City city){

        int index = 0;
        List<Intersection> intersectionList = city.getIntersectionList();
        List<Street> streetList = new ArrayList<>(city.getStreetList());
        List<Street> solution = new ArrayList<>();
        List<Intersection> roots = new ArrayList<>();

        for(int i = 0; i < intersectionList.size(); i++)
            roots.add(intersectionList.get(i));

        streetList.sort(Street::compareTo);

        while(index < streetList.size()){

            Street currentStreet = streetList.get(index);
            int firstRootIndex = intersectionList.indexOf(currentStreet.getFirst());
            int secondRootIndex = intersectionList.indexOf(currentStreet.getSecond());
            Intersection firstRoot = roots.get(firstRootIndex);
            Intersection secondRoot = roots.get(secondRootIndex);

            if( !firstRoot.equals(secondRoot) ){
                solution.add(currentStreet);
                roots.set(firstRootIndex, currentStreet.getFirst());
                roots.set(secondRootIndex, currentStreet.getFirst());
                for(int i = 0; i < roots.size(); i++){
                    Intersection root = roots.get(i);
                    if(root.equals(firstRoot) || root.equals(secondRoot))
                        roots.set(i, currentStreet.getFirst());
                }
            }
            index++;
        }

        return solution;
    }


}