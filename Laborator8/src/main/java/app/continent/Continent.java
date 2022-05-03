package app.continent;

import app.generic.Location;

public class Continent implements Location {
    private Integer id;
    private String name;

    public Continent(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Continent(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Continent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
