package app.client.repository;

import app.client.entity.Client;
import app.persistanceUtil.PersistanceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class ClientRepository {

    public void create(Client client) throws Exception{
        EntityManager entityManager = PersistanceUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.getTransaction().commit();
        }catch (Exception exception){
            throw exception;
        }finally {
            entityManager.close();
        }
    }

    public Client save(Client client) throws Exception{
        EntityManager entityManager = PersistanceUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(client);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
        return new ClientRepository().findByName(client.getName());
    }

    public Client findById(Integer id) {
        return (Client) PersistanceUtil.getEntityManager().createNamedQuery("Client.findById")
                .setParameter(1, id)
                .getSingleResult();
    }

    public Client findByName(String name) {
        return (Client) PersistanceUtil.getEntityManager().createNamedQuery("Client.findByName")
                .setParameter(1, name)
                .getSingleResult();
    }
}
