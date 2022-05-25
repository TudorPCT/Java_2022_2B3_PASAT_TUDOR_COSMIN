package com;

import com.controller.ClientService;
import com.model.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientLab11Application {

    public static void main(String[] args) {
        SpringApplication.run(ClientLab11Application.class, args);
        ClientService clientService = new ClientService();
        clientService.addClient("Ralucaa");
        clientService.addClient("Tocila");
        clientService.addClient("Fomin");
        clientService.getClients();
        clientService.updateClients(2L,"Galan");
        clientService.getClients();
        clientService.delete(3L);
        clientService.getClients();
        clientService.addFriend(2L,1L,"Ralucaa");
        clientService.getFriends(2L);
        clientService.kPopular(2);
    }



}
