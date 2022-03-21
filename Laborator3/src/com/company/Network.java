package com.company;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Network {
    private List<Node> nodes = new ArrayList<>();

    public void addNode(Node node){
        for(int i = 0; i < nodes.size(); i++)
            if(nodes.get(i).getName().equals(node.getName()))
                return;
        nodes.add(node);
    }

    public void setCost(int nodeIndex, int cost, Node node){
        nodes.get(nodeIndex).setCost(node,cost);
    }

    public String getNetworkCosts(){
        StringBuilder str = new StringBuilder();
        str.append("------------------\n| From-To | Cost | \n------------------\n");
        for(int i = 0; i < nodes.size(); i++)
            for(int j = i; j < nodes.size(); j++)
                if(nodes.get(i).getCost(nodes.get(j)) != null)
                    str.append("| ").append(nodes.get(i).getLocation()).append("--").append(nodes.get(j).getLocation()).append("  |  ").append(nodes.get(i).getCost(nodes.get(j))).append("  |\n");
        str.append("------------------\n");
        return str.toString();
    }

    public void printIdentifiableNodes(){
        System.out.println(getIdentifiableNodes());
    }

    public List<Node> getIdentifiableNodes(){
        List<Node> identifiableNodes = new ArrayList<>();
        for(Node node : nodes)
            if(node instanceof Identifiable)
                identifiableNodes.add(node);
        Collections.sort(identifiableNodes, (u,v) -> ((Identifiable) u).getAddress().compareTo(((Identifiable) u).getAddress()));
        return identifiableNodes;
    }

    public void printShortestTimes(){

        List<Node> identifiableNodes = getIdentifiableNodes();

        System.out.println("-----------------------------\n|      From-To         | Cost  \n-----------------------------");

        for(Node node : identifiableNodes){
            int[] pathCost = getShortestPathCosts(node);
            for(Node neighbour : identifiableNodes){
                if(!node.getName().equals(neighbour.getName()))
                    System.out.println("| " + node.getName() + "--" + neighbour.getName() + " | " + pathCost[nodes.indexOf(neighbour)]);

            }
            System.out.println();
        }
    }

    public int[] getShortestPathCosts(Node node){
        List<Node> unvisitedNodes = new ArrayList<>(nodes);
        Node[] before = new Node[nodes.size()];
        int[] pathCost = new int[nodes.size()];
        int index = nodes.indexOf(node);
        unvisitedNodes.remove(node);
        before[index] = null;
        pathCost[index] = 0;
        Integer cost;
        for(Node i : unvisitedNodes) {
            int currentIndex = nodes.indexOf(i);
            if((cost = node.getCost(i)) != null)
                pathCost[currentIndex] = node.getCost(i);
            else
                pathCost[currentIndex] = Integer.MAX_VALUE;
            before[currentIndex] = node;
        }

        while(!unvisitedNodes.isEmpty()){
            int minimum = Integer.MAX_VALUE;
            Node candidate = null;
            for(Node i : unvisitedNodes) {
                int currentIndex = nodes.indexOf(i);
                if (minimum > pathCost[currentIndex]) {
                    minimum = pathCost[currentIndex];
                    candidate = i;
                }
            }
            unvisitedNodes.remove(candidate);
            for(Node i : unvisitedNodes){
                int currentIndex = nodes.indexOf(i);
                if(candidate.getCost(i) != null && pathCost[currentIndex] > minimum + candidate.getCost(i)){
                    pathCost[currentIndex] = minimum + candidate.getCost(i);
                    before[nodes.indexOf(i)] = candidate;
                }
            }
        }
        return pathCost;
    }

    @Override
    public String toString() {
        return "Network{" + '\n' +
                "nodes=" + nodes + '\n' +
                '}';
    }
}
