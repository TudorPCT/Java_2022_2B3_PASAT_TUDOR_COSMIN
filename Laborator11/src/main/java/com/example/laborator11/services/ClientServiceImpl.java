package com.example.laborator11.services;

import com.example.laborator11.model.Client;
import com.example.laborator11.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
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
    public List<Client> getFriends(Long id) {
        return clientRepository.getById(id).getFriends();
    }

    @Override
    public void addFriend(Client client1, Client client2) {
        client1.addFriend(client2);
    }

    @Override
    public Client addClients(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        Client client1= clientRepository.findById(client.getId()).get();
        client1.setName(client.getName());
        return client1;
    }

    @Override
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }


}
