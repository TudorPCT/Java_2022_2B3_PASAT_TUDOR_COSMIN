package com.example.laborator11.services;

import com.example.laborator11.model.Client;
import com.example.laborator11.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientServices{

    private final ClientRepository clientRepository;

    @Override
    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id){
        return clientRepository.findById(id).get();
    }

    @Override
    public Client findByName(String name) {
        return clientRepository.findByName(name);
    }

    @Override
    public List<Client> getFriends(Long id) {
        return clientRepository.getById(id).getFriends();
    }

    @Override
    public Long nrOfFriends(Client client) {
        List<Client> friends=this.getFriends(client.getId());
        return (long) friends.size();
    }

    @Override
    public List<Client> kPopular(int k) {
        List<Client> clients=this.getClients();
        clients.sort(Comparator.comparing(this::nrOfFriends).reversed());
        return clients.subList(0,k);
    }

    @Override
    public void addFriend(Client client1, Client client2) {
        client1.addFriend(client2);
        client2.addFriend(client1);
        clientRepository.save(client1);
        clientRepository.save(client2);

    }

    @Override
    public Client addClients(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        clientRepository.save(client);
        return client;
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

//    @Override
//    public List<Client> getMostPopularK(Integer k) {
//        return clientRepository.findMostPopularK(k);
//    }


}
