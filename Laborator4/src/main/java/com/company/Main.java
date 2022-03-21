package com.company;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args){
        Main lab4 = new Main();
        lab4.compulsory();
    }

    public void compulsory(){

        var nodes = IntStream.rangeClosed(0, 8)
                .mapToObj(i -> new Intersection("v" + i) )
                .toArray(Intersection[]::new);

        List<Street> streetList = createStreetsList(nodes);

        streetList.sort(Street::compareTo);

        Set<Intersection> nodeSet = new HashSet<>();

        nodeSet.addAll(Arrays.asList(nodes));

        System.out.println(nodeSet);

        nodeSet.add(nodes[0]);

        System.out.println(nodeSet);

    }

    public List<Street> createStreetsList(Intersection[] nodes){
        List<Street> streetList = new ArrayList<>();

        streetList.add(new Street("s0",2, new Intersection[] {nodes[0],nodes[1]}));
        streetList.add(new Street("s1",2, new Intersection[] {nodes[0],nodes[2]}));
        streetList.add(new Street("s2",2, new Intersection[] {nodes[0],nodes[3]}));
        streetList.add(new Street("s3",2, new Intersection[] {nodes[1],nodes[2]}));
        streetList.add(new Street("s4",1, new Intersection[] {nodes[2],nodes[3]}));
        streetList.add(new Street("s5",3, new Intersection[] {nodes[1],nodes[4]}));
        streetList.add(new Street("s6",2, new Intersection[] {nodes[2],nodes[5]}));
        streetList.add(new Street("s7",2, new Intersection[] {nodes[2],nodes[6]}));
        streetList.add(new Street("s8",3, new Intersection[] {nodes[3],nodes[6]}));
        streetList.add(new Street("s9",1, new Intersection[] {nodes[4],nodes[6]}));
        streetList.add(new Street("s10",1, new Intersection[] {nodes[4],nodes[7]}));
        streetList.add(new Street("s11",2, new Intersection[] {nodes[4],nodes[8]}));
        streetList.add(new Street("s12",1, new Intersection[] {nodes[5],nodes[7]}));
        streetList.add(new Street("s13",1, new Intersection[] {nodes[5],nodes[8]}));
        streetList.add(new Street("s14",1, new Intersection[] {nodes[7],nodes[8]}));
        streetList.add(new Street("s15",3, new Intersection[] {nodes[6],nodes[8]}));

        return streetList;
    }
}
