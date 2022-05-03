package app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "continents")
@NamedQueries({
        @NamedQuery(name = "Continent.findById",
                query = "select e from Continent e where e.id = ?1"),
        @NamedQuery(name = "Continent.findByName",
                query = "select e from Continent e where e.name = ?1")
})
public class Continent implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;

    public Continent(){}

    public Continent(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Continent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
