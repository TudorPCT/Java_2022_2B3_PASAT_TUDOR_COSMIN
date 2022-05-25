package com.example.laborator11.controller;

import com.example.laborator11.model.Client;
import com.example.laborator11.services.ClientServices;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class ClientController {

    private final ClientServices clientServices;

    @GetMapping
    public ResponseEntity<List<Client>> getClients(){
        return ResponseEntity.ok().body(clientServices.getClients());
    }

    @PostMapping
    public ResponseEntity<Client> addClients(@RequestBody Client client) {
        if (client == null ||client.getName()==null)
            return ResponseEntity.badRequest().body(client);
        return ResponseEntity.ok().body(clientServices.addClients(client));
    }

    @PutMapping
    public ResponseEntity<Client> updateClients(@RequestBody Client client){
        if(client == null || client.getName() == null)
            return ResponseEntity.badRequest().body(client);
        return ResponseEntity.ok().body(clientServices.update(client));
    }

    @DeleteMapping
    public void delete(@RequestBody ObjectNode objectNode){
        Long id = objectNode.get("id").asLong();
        clientServices.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(clientServices.findById(id));
    }

    @GetMapping("/{id}/friends")
    public ResponseEntity<List<Client>>getFriends(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(clientServices.getFriends(id));
    }
    @GetMapping("/popular/{k}")
    public ResponseEntity<List<Client>>kPopular(@PathVariable("k") int k){
        return ResponseEntity.ok().body(clientServices.kPopular(k));
    }

    @PutMapping("/{id}/friends")
        public ResponseEntity<String> addFriend( @PathVariable("id") Long id, @RequestBody Client friend) {
        Client client = clientServices.findById(id);
        Client friend1 = clientServices.findByName(friend.getName());
        if (client == null)
            return new ResponseEntity<>("Client with the id " + id + " couldn't be found", HttpStatus.NOT_FOUND);
        if (friend1 == null)
            return new ResponseEntity<>("Client " + friend.getName() + " couldn't be found", HttpStatus.NOT_FOUND);

        clientServices.addFriend(client,friend1);
        return new ResponseEntity<>("Friendship updated successfully", HttpStatus.OK);
    }

}
