package app.repositories;

import app.entity.City;
import app.entity.Country;
import app.persistanceUtil.PersistanceUtil;

import javax.persistence.EntityManager;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class CityRepository extends DataRepository<City,Integer>{

    public City findById(Integer id) {
        return (City) PersistanceUtil.getEntityManager().createNamedQuery("City.findById")
                .setParameter(1, id)
                .getSingleResult();
    }

    public List<City> findByName(String name) {
        return PersistanceUtil.getEntityManager().createNamedQuery("City.findByName")
                .setParameter(1, name)
                .getResultList();
    }

    public List<City> findByPopulation(int population) {
        return PersistanceUtil.getEntityManager().createNamedQuery("City.findByPopulation")
                .setParameter(1, population)
                .getResultList();
    }

    public List<City> findAll(){
        return PersistanceUtil.getEntityManager().createNamedQuery("City.findAll")
                .getResultList();
    }
    

    public void populateFromDataSet(String filename) throws IOException, SQLException {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line = " ";
        String[] tempArr;

        if ((line = br.readLine()) != null
                && !line.equals("CountryName,CapitalName,CapitalLatitude,CapitalLongitude,CountryCode,ContinentName")) {
            throw new RuntimeException("Expected a file with the header CountryName,CapitalName,CapitalLatitude,CapitalLongitude,CountryCode,ContinentName");
        }
        while ((line = br.readLine()) != null) {
            tempArr = line.split(",");
            if (tempArr.length != 6
                    || tempArr[1].equals("N/A")
                    || !this.findByName(tempArr[1]).isEmpty())
                continue;
            try {
                this.save(new City(
                        tempArr[1],
                        new CountryRepository().findByName(tempArr[0]).get(0),
                        1,
                        Double.parseDouble(tempArr[2]),
                        Double.parseDouble(tempArr[3]),
                        1_000 + (int)(Math.random() * 1_000_000)));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        br.close();
    }

}
