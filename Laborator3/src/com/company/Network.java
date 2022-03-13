package com.company;

import java.util.ArrayList;
import java.util.List;

public class Network {
    private List<Node> nodes = new ArrayList<>();

    public void addNode(Node node){
        for(int i = 0; i < nodes.size(); i++)
            if(nodes.get(i).getName().equals(node.getName()))
                return;
        nodes.add(node);
    }

    @Override
    public String toString() {
        return "Network{" +
                "nodes=" + nodes +
                '}';
    }
}
