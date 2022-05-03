package app.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "countries")
@NamedQueries({
        @NamedQuery(name = "Country.findById",
                query = "select e from Country e where e.id = ?1"),
        @NamedQuery(name = "Country.findByName",
                query = "select e from Country e where e.name = ?1")
})
public class Country implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "id")
    @Column(name = "id")
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "code")
    private String code;
    @Column(name = "continent")
    private Integer continent;

    public Country(){}

    public Country(String name, String code, Integer continent) {
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

    public Integer getContinent() {
        return continent;
    }

    public void setContinent(Integer continent) {
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
