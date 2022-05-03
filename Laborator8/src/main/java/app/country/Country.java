package app.country;

import app.generic.Location;

public class Country implements Location {
    private Integer id;
    private String name;
    private String code;
    private Integer continent;

    public Country(Integer id, String name, String code, Integer continent) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.continent = continent;
    }

    public Country(String name, String code, Integer continent) {
        this.name = name;
        this.code = code;
        this.continent = continent;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public Integer getContinent() {
        return continent;
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
