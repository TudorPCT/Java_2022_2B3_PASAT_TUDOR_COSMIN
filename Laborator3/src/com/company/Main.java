package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	    Main lab3 = new Main();
       // lab3.compulsory();
        lab3.homework();
    }

    public void compulsory(){
        Network network = new Network();
        network.addNode(new Computer("Computer A", "v1","123",10, null));
        network.addNode(new Router("Router A", "v2","126", null));
        network.addNode(new Switch("Switch A", "v3", null));
        network.addNode(new Switch("Switch B", "v4", null));
        network.addNode(new Router("Router B", "v5","127", null));
        network.addNode(new Computer("Computer B", "v6","130",50, null));
        System.out.println(network);
    }

    public void homework(){

        int[][] costMatrix = createCostMatrix();
        Network network = new Network();
        List<Node> nodes = new ArrayList<>();

        nodes.add(new Computer("Computer A", "v1","123",10, new HashMap<>()));
        nodes.add(new Router("Router A", "v2","126", new HashMap<>()));
        nodes.add(new Switch("Switch A", "v3", new HashMap<>()));
        nodes.add(new Switch("Switch B", "v4", new HashMap<>()));
        nodes.add(new Router("Router B", "v5","127", new HashMap<>()));
        nodes.add(new Computer("Computer B", "v6","130",50, new HashMap<>()));


        for(int i = 0; i < nodes.size(); i++) {
            for (int j = 0; j < nodes.size(); j++)
                if(costMatrix[i][j] > 0)
                    nodes.get(i).setCost(nodes.get(j), costMatrix[i][j]);
            network.addNode(nodes.get(i));
        }

        System.out.println(network.getNetworkCosts());

        System.out.println(((Computer) nodes.get(0)).getStorageCapacityAlternative("megabyte"));
        System.out.println();

        network.printIdentifiableNodes();
        System.out.println();

        network.printShortestTimes();

    }

    public int[][] createCostMatrix(){
        int[][] costMatrix = new int[6][6];
        for(int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                if (i == j)
                    costMatrix[i][j] = 0;
                else
                    costMatrix[i][j] = -1;
            }
        }

        costMatrix[0][1] = costMatrix[1][0] = 10;
        costMatrix[0][2] = costMatrix[2][0] = 50;
        costMatrix[1][2] = costMatrix[2][1] = 20;
        costMatrix[1][3] = costMatrix[3][1] = 20;
        costMatrix[1][4] = costMatrix[4][1] = 20;
        costMatrix[2][3] = costMatrix[3][2] = 10;
        costMatrix[3][4] = costMatrix[4][3] = 30;
        costMatrix[3][5] = costMatrix[5][3] = 10;
        costMatrix[4][5] = costMatrix[5][4] = 20;

        return costMatrix;
    }
}
