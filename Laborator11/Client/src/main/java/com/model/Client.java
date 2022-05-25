package com.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {

    Long id;
    String name;
    List<Client> friends;

    public void addFriend(Client client)
    {
        friends.add(client);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
