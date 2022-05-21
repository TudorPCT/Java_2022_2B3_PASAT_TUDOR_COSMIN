package com.example.laborator11.services;

import com.example.laborator11.model.Client;

import java.util.List;
import java.util.Optional;

public interface ClientServices {
    List<Client> getClients();
    Client addClients(Client client);
    Client update(Client client);
    Client findById(Long id);
    List<Client> getFriends(Long id);
    void addFriend(Client client1,Client client2);
    void delete(Long id);
}
