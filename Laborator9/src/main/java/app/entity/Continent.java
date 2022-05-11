package app.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "continents")
@NamedQueries({
        @NamedQuery(name = "Continent.findById",
                query = "select e from Continent e where e.id = ?1"),
        @NamedQuery(name = "Continent.findByName",
                query = "select e from Continent e where e.name = ?1")
})
public class Continent extends AbstractEntity {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "continent")
    private List<Country> countries;

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
