package com.example.laborator11.controller;

import com.example.laborator11.model.Client;
import com.example.laborator11.services.ClientServices;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;



@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
public class AdminController {

    private final ClientServices clientServices;

    @GetMapping()
    public ResponseEntity<List<Client>> getClients(){
        return ResponseEntity.ok().body(clientServices.getClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(clientServices.findById(id));
    }

    @GetMapping("/{id}/getFriends")
    public ResponseEntity<List<Client>>getFriends(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(clientServices.getFriends(id));
    }

    @PostMapping()
    public ResponseEntity<Client> addClients(@RequestBody Client client) {
        if (client == null ||client.getName()==null)
            return ResponseEntity.badRequest().body(client);
        return ResponseEntity.ok().body(clientServices.addClients(client));
    }

    @PutMapping("/{id}/addFriend")
        public ResponseEntity<String> updateProduct( @PathVariable("id") Long id, @RequestBody Client client) {
        if (client == null ||client.getName()==null)
            return new ResponseEntity<>("Client not found", HttpStatus.NOT_FOUND);
        else
        {
            clientServices.addFriend(clientServices.findById(id),client);
        return new ResponseEntity<>("Friendship updated successsfully", HttpStatus.OK);
        }
    }

    @PutMapping()
    public ResponseEntity<Client> updateClients(@RequestBody Client client){
        if(client==null||client.getName()==null)
            return ResponseEntity.badRequest().body(client);
        return ResponseEntity.ok().body(clientServices.update(client));
    }
    @DeleteMapping()
    public void delete(@RequestBody ObjectNode objectNode){
        Long id = objectNode.get("id").asLong();
        clientServices.delete(id);
    }
}
