package app.entity;

import app.persistanceUtil.TimeLogger;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@EntityListeners({TimeLogger.class})

@Table(name = "cities")
@NamedQueries({
        @NamedQuery(name = "City.findById",
                query = "select e from City e where e.id = ?1"),
        @NamedQuery(name = "City.findByName",
                query = "select e from City e where e.name = ?1"),
        @NamedQuery(name = "City.findAll",
                query = "select e from City e"),
        @NamedQuery(name = "City.findByPopulation",
                query = "select e from City e where e.population = ?1")
})
public class City extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "country",nullable = false)
    private Country country;
    @Column(name = "capital")
    private Integer capital;
    @Column(name = "latitude")
    private Double latitude;
    @Column(name = "longitude")
    private Double longitude;
    @Column(name = "population")
    private Integer population;

    public City(){}

    public City(String name, Country country, Integer capital, Double latitude, Double longitude, Integer population) {
        this.name = name;
        this.country = country;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
        this.population = population;
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

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Integer getCapital() {
        return capital;
    }

    public void setCapital(Integer capital) {
        this.capital = capital;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country=" + country +
                ", capital=" + capital +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
