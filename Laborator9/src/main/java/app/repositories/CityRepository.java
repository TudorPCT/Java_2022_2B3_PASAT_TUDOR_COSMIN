package app.repositories;

import app.entity.City;
import app.persistanceUtil.PersistanceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CityRepository {

    public void create(City city) {
        EntityManager entityManager = PersistanceUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(city);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

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

}
