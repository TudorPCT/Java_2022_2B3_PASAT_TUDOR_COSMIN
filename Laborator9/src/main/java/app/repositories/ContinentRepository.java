package app.repositories;

import app.entity.Continent;
import app.persistanceUtil.PersistanceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ContinentRepository {

    public void create(Continent continent){
        EntityManager entityManager = PersistanceUtil.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(continent);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Continent findById(Integer id) {
        return (Continent) PersistanceUtil.getEntityManager().createNamedQuery("Continent.findById")
                .setParameter(1, id)
                .getSingleResult();
    }

    public List<Continent> findByName(String name) {
        return PersistanceUtil.getEntityManager().createNamedQuery("Continent.findByName")
                .setParameter(1, name)
                .getResultList();
    }
}
