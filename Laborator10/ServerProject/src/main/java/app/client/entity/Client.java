package app.client.entity;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "client")
@NamedQueries({
        @NamedQuery(name = "Client.findById",
                query = "select e from Client e where e.id = ?1"),
        @NamedQuery(name = "Client.findByName",
                query = "select e from Client e where e.name = ?1")
})
public class Client {
    @Id
    @GeneratedValue(generator="my_seq")
    @SequenceGenerator(name="my_seq",sequenceName="MY_SEQ", allocationSize=1)
    private Long id = null;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    private String name;

    @ManyToMany(
            cascade={CascadeType.MERGE},
            targetEntity=app.client.entity.Client.class
    )
    @JoinTable(
            name = "relations",
            joinColumns = @JoinColumn(name = "id_friend1"),
            inverseJoinColumns = @JoinColumn(name = "id_friend2"))
    private final List<Client> friends = new ArrayList<>();

    @ManyToMany(
            cascade={CascadeType.PERSIST, CascadeType.MERGE},
            targetEntity=app.client.entity.Client.class
    )
    @JoinTable(
            name = "relations",
            joinColumns = @JoinColumn(name = "id_friend2"),
            inverseJoinColumns = @JoinColumn(name = "id_friend1"))
    private final List<Client> friendsOf = new ArrayList<>();

    public Client(){}
    public Client(String name){
        this.name = name;
    }

    public void addFriend(Client client){
        this.friends.add(client);
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public List<Client> getFriends() {
        List<Client> allFriends = new ArrayList<>();
        allFriends.addAll(friendsOf);
        allFriends.addAll(friends);
        return allFriends;
    }
}
