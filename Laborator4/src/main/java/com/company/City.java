package com.company;

import java.util.*;

public class City {
    protected List<Intersection> intersectionsList;
    protected List<Street> streetList;
    protected Map<Intersection, List<Street>> cityMap;

    public City( List<Intersection> newIntersectionsList,  List<Street> newStreetList, Map<Intersection, List<Street>> newCityMap){
        intersectionsList = newIntersectionsList;
        streetList = newStreetList;
        cityMap = newCityMap;
    }

    public City(List<Intersection> newIntersectionsList,  List<Street> newStreetList){
        this(newIntersectionsList, newStreetList, new HashMap<>());
    }

    public void addIntersection(Intersection intersection, List<Street> streetList){
        cityMap.put(intersection, streetList);
    }

    public void printStreetsByLength(int length){
        streetList.stream()
                .filter(s -> s.getLength() > length)
                .filter(s -> (cityMap.get(s.getFirst()).size() + cityMap.get(s.getSecond()).size() - 2) > 2)
                .forEach(System.out::println);
    }

    public List<Street> getStreetList(){
        return streetList;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityMap=" + cityMap +
                '}';
    }

    public List<Intersection> getIntersectionList() {
        return intersectionsList;
    }
}
