package app.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "countries")
@NamedQueries({
        @NamedQuery(name = "Country.findById",
                query = "select e from Country e where e.id = ?1"),
        @NamedQuery(name = "Country.findByName",
                query = "select e from Country e where e.name = ?1")
})
public class Country extends AbstractEntity {
    @Column(name = "code")
    private String code;
    @ManyToOne
    @JoinColumn(name = "continent",nullable = false)
    private Continent continent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "country")
    private List<City> cities;

    public Country(){}

    public Country(String name, String code, Continent continent) {
        this.name = name;
        this.code = code;
        this.continent = continent;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", continent=" + continent +
                '}';
    }
}
