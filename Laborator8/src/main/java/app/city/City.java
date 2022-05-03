package app.city;

import app.generic.Location;

public class City implements Location {
    private Integer id;
    private String name;
    private Integer country;
    private Integer capital;
    private Double latitude;
    private Double longitude;

    public City(Integer id, String name, Integer country, Integer capital, Double latitude, Double longitude) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public City(String name, Integer country, Integer capital, Double latitude, Double longitude) {
        this.name = name;
        this.country = country;
        this.capital = capital;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getCountry() {
        return country;
    }

    public Integer getCapital() {
        return capital;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
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

    public Double distanceBetween(City city) {
        double latitudeCityOne = Math.toRadians(this.latitude);
        double longitudeCityOne = Math.toRadians(this.longitude);
        double latitudeCityTwo = Math.toRadians(city.getLatitude());
        double longitudeCityTwo = Math.toRadians(city.getLongitude());

        double dlon = longitudeCityTwo - longitudeCityOne;
        double dlat = latitudeCityTwo - latitudeCityOne;

        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(latitudeCityOne) * Math.cos(latitudeCityTwo)
                * Math.pow(Math.sin(dlon / 2),2);

        double c = 2 * Math.asin(Math.sqrt(a));
        double r = 6371;

        return(c * r);
    }
}
