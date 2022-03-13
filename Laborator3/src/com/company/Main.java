package com.company;

public class Main {

    public static void main(String[] args) {
	    Main lab3 = new Main();
        lab3.compulsory();
    }

    public void compulsory(){
        Network network = new Network();
        network.addNode(new Computer("Computer A", "v1","123",10));
        network.addNode(new Router("Router A", "v2","126"));
        network.addNode(new Switch("Switch A", "v3"));
        network.addNode(new Switch("Switch B", "v4"));
        network.addNode(new Router("Router B", "v5","127"));
        network.addNode(new Computer("Computer B", "v6","130",50));
        System.out.println(network);
    }
}
