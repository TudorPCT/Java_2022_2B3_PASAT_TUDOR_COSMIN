package app.repositories;

import app.entity.Country;
import app.persistanceUtil.PersistanceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class CountryRepository {

    public void create(Country country) {
        EntityManager entityManager = PersistanceUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(country);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Country findById(Integer id) {
        return (Country) PersistanceUtil.getEntityManager().createNamedQuery("Country.findById")
                .setParameter(1, id)
                .getSingleResult();
    }

    public List<Country> findByName(String name) {
        return PersistanceUtil.getEntityManager().createNamedQuery("Country.findByName")
                .setParameter(1, name)
                .getResultList();
    }
}
