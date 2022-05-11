package app.repositories;

import app.entity.AbstractEntity;
import app.persistanceUtil.PersistanceUtil;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

public abstract class DataRepository <T extends AbstractEntity, ID extends Serializable> {

    public void save(T entity) throws Exception{
        EntityManager entityManager = PersistanceUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.getTransaction().commit();
        }catch (Exception exception){
            throw exception;
        }finally {
            entityManager.close();
        }
    }

    public abstract T findById(ID id);

    public abstract List<T> findByName(String name);

}