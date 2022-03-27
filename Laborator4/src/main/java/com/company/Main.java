package com.company;
import com.github.javafaker.Faker;
import org.apache.commons.lang3.tuple.Pair;

import java.util.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args){
        Main lab4 = new Main();
    //    lab4.compulsory();
        lab4.homework();
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

    public void homework(){
        City city = createCity();
      //  city.printStreetsByLength(2);

        Algorithm alg = new Algorithm();
        System.out.println(alg.kruskal(city));
    }

    public City createCity(){

        Faker faker = new Faker();
        List<Intersection> intersectionList = new ArrayList<>();
        List<Street> streetList;
        City city;

        for (int i = 0; i <= 8; i++)
            intersectionList.add(new Intersection(faker.address().streetName()+i));

        streetList = createStreetsListFaker(intersectionList,faker);

        city = new City(intersectionList,streetList);
        city.addIntersection(intersectionList.get(0),Arrays.asList(streetList.get(0), streetList.get(1), streetList.get(2)));
        city.addIntersection(intersectionList.get(1),Arrays.asList(streetList.get(0), streetList.get(3), streetList.get(5)));
        city.addIntersection(intersectionList.get(2),Arrays.asList(streetList.get(1), streetList.get(3), streetList.get(4), streetList.get(6), streetList.get(7)));
        city.addIntersection(intersectionList.get(3),Arrays.asList(streetList.get(2), streetList.get(4), streetList.get(8)));
        city.addIntersection(intersectionList.get(4),Arrays.asList(streetList.get(5), streetList.get(9), streetList.get(10), streetList.get(11)));
        city.addIntersection(intersectionList.get(5),Arrays.asList(streetList.get(6), streetList.get(12), streetList.get(13)));
        city.addIntersection(intersectionList.get(6),Arrays.asList(streetList.get(7), streetList.get(8), streetList.get(9), streetList.get(15)));
        city.addIntersection(intersectionList.get(7),Arrays.asList(streetList.get(10), streetList.get(12), streetList.get(14)));
        city.addIntersection(intersectionList.get(8),Arrays.asList(streetList.get(11), streetList.get(13), streetList.get(14), streetList.get(15)));

        return city;
    }

    public List<Street> createStreetsListFaker(List<Intersection> nodes, Faker faker){

        List<Street> streetList = new ArrayList<>();
        streetList.add(new Street(faker.address().streetName(),2, new Intersection[] {nodes.get(0), nodes.get(1)}));
        streetList.add(new Street(faker.address().streetName(),2, new Intersection[] {nodes.get(0), nodes.get(2)}));
        streetList.add(new Street(faker.address().streetName(),2, new Intersection[] {nodes.get(0), nodes.get(3)}));
        streetList.add(new Street(faker.address().streetName(),2, new Intersection[] {nodes.get(1), nodes.get(2)}));
        streetList.add(new Street(faker.address().streetName(),1, new Intersection[] {nodes.get(2), nodes.get(3)}));
        streetList.add(new Street(faker.address().streetName(),3, new Intersection[] {nodes.get(1), nodes.get(4)}));
        streetList.add(new Street(faker.address().streetName(),2, new Intersection[] {nodes.get(2), nodes.get(5)}));
        streetList.add(new Street(faker.address().streetName(),2, new Intersection[] {nodes.get(2), nodes.get(6)}));
        streetList.add(new Street(faker.address().streetName(),3, new Intersection[] {nodes.get(3), nodes.get(6)}));
        streetList.add(new Street(faker.address().streetName(),1, new Intersection[] {nodes.get(4), nodes.get(6)}));
        streetList.add(new Street(faker.address().streetName(),1, new Intersection[] {nodes.get(4), nodes.get(7)}));
        streetList.add(new Street(faker.address().streetName(),2, new Intersection[] {nodes.get(4), nodes.get(8)}));
        streetList.add(new Street(faker.address().streetName(),1, new Intersection[] {nodes.get(5), nodes.get(7)}));
        streetList.add(new Street(faker.address().streetName(),1, new Intersection[] {nodes.get(5), nodes.get(8)}));
        streetList.add(new Street(faker.address().streetName(),1, new Intersection[] {nodes.get(7), nodes.get(8)}));
        streetList.add(new Street(faker.address().streetName(),3, new Intersection[] {nodes.get(6), nodes.get(8)}));

        return streetList;
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
