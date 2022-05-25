package com.controller;

import com.model.Client;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
public class ClientService {
    final Logger log = LoggerFactory.getLogger(ClientService.class);
    String uri="http://localhost:8082/client";

    RestTemplate restTemplate= new RestTemplate();

    @GetMapping()
    public List<Client> getClients()
    {
        log.info("Start");

        ResponseEntity<List<Client>> response = restTemplate.exchange(
                uri, HttpMethod.GET, null,
                new ParameterizedTypeReference<>(){});
        List<Client> result = response.getBody();
        result.forEach(p -> log.info(p.toString()));
        log.info("Stop");
        return result;
    }
    //save
    @PostMapping()
    public ResponseEntity<String> addClient (String name)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject jsonObject = new JSONObject();
        jsonObject .put("name", name);

        HttpEntity<JSONObject> entity = new HttpEntity<>(jsonObject , headers);

        ResponseEntity<String> response=restTemplate.exchange(uri,HttpMethod.POST,entity,String.class);
        log.info(response.getBody());
        return response;
    }

    //update
    @PutMapping()
    public ResponseEntity<String> updateClients(Long id,String name)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id);
        jsonObject .put("name", name);

        HttpEntity<JSONObject> entity = new HttpEntity<>(jsonObject , headers);

        ResponseEntity<String> response=restTemplate.exchange(uri,HttpMethod.PUT,entity,String.class);
        log.info(response.getBody());
        return response;

    }

    //delete person
    @DeleteMapping()
    public ResponseEntity<String> delete(Long id)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject jsonObject = new JSONObject();
        jsonObject .put("id", id);

        HttpEntity<JSONObject> entity = new HttpEntity<>(jsonObject , headers);


        ResponseEntity<String> response=restTemplate.exchange(uri,HttpMethod.DELETE,entity,String.class);
        log.info(response.getBody());
        return response;

    }
    //get friends
    @GetMapping("{id}/friends")
    public List<Client> getFriends(Long id)
    {
        String tempUri= uri+"/"+id+"/friends";
        log.info("Start");

        ResponseEntity<List<Client>> response = restTemplate.exchange(
                tempUri, HttpMethod.GET, null,
                new ParameterizedTypeReference<>(){});
        List<Client> result = response.getBody();
        result.forEach(p -> log.info(p.toString()));
        log.info("Stop");
        return result;
    }

    //add friends
    @PutMapping("{id}/friends")
    public ResponseEntity<String> addFriend(Long id1,Long id2,String name)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        String tempUri= uri+"/"+id1+"/friends";

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id",id2);
        jsonObject .put("name", name);

        HttpEntity<JSONObject> entity = new HttpEntity<>(jsonObject , headers);

        ResponseEntity<String> response= restTemplate.<String>exchange(tempUri, HttpMethod.PUT, entity, String.class);
        log.info(response.getBody());
        return response;

    }
//    @GetMapping("/popular/{k}")
    public List<Client> kPopular(int k) {
        String tempUri= uri+"/popular/"+k;
        log.info("Start");

        ResponseEntity<List<Client>> response = restTemplate.exchange(
                tempUri, HttpMethod.GET, null,
                new ParameterizedTypeReference<>(){});
        List<Client> result = response.getBody();
        result.forEach(p -> log.info(p.toString()));
        log.info("Stop");
        return result;
    }
}

