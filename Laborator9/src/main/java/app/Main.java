package app;

import app.choco.ChocoSolver;
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
import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        Main lab9 = new Main();
        //  lab9.compulsory();
        lab9.homework();
    }

    private void homework() {
        ChocoSolver chocoSolver = new ChocoSolver();
        chocoSolver.solve(500_000, 501_500);

    }

    private void compulsory() {
        ContinentRepository continentRepository = new ContinentRepository();
        try {
            continentRepository.save(new Continent("asd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        CountryRepository countryRepository = new CountryRepository();
        System.out.println(countryRepository.findByName("Romania"));
        CityRepository cityRepository = new CityRepository();
        System.out.println(cityRepository.findById(14));
    }
}
