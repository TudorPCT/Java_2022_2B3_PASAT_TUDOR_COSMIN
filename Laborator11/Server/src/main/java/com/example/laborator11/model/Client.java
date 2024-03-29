package com.example.laborator11.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    @Id
    @GeneratedValue(generator="my_seq")
    @SequenceGenerator(name="my_seq",sequenceName="MY_SEQ", allocationSize=1)
    private Long id = null;

    private String name;

    @ManyToMany(
            cascade={CascadeType.MERGE},
            targetEntity= Client.class
    )
    @JoinTable(
            name = "relations",
            joinColumns = @JoinColumn(name = "id_friend1"),
            inverseJoinColumns = @JoinColumn(name = "id_friend2"))
    @JsonIgnore
    private final List<Client> friends = new ArrayList<>();

    public void addFriend(Client client){
        this.friends.add(client);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
