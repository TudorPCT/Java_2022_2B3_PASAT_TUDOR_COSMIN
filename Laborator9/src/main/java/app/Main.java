package app;

import app.entity.City;
import app.entity.Continent;
import app.entity.Country;
import app.repositories.CityRepository;
import app.repositories.ContinentRepository;
import app.repositories.CountryRepository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.text.html.parser.Entity;

public class Main {
    public static void main(String[] args){
        ContinentRepository continentRepository = new ContinentRepository();
        continentRepository.create(new Continent("asd"));
        CountryRepository countryRepository = new CountryRepository();
        System.out.println(countryRepository.findByName("Romania"));
        CityRepository cityRepository = new CityRepository();
        System.out.println(cityRepository.findById(14));

    }
}
