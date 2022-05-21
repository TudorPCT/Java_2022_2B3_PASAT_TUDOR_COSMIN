package app.client.repository;

import app.client.entity.Client;
import app.client.entity.Message;
import app.persistanceUtil.PersistanceUtil;

import javax.persistence.EntityManager;
import java.util.List;

public class MessageRepository {
    public void create(Message message) throws Exception{
        EntityManager entityManager = PersistanceUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(message);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }
    public void save(Message message) throws Exception{
        EntityManager entityManager = PersistanceUtil.getEntityManager();
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(message);
            entityManager.getTransaction().commit();
        } finally {
            entityManager.close();
        }
    }

    public Message findById(Integer id) {
        return (Message) PersistanceUtil.getEntityManager().createNamedQuery("Message.findById")
                .setParameter(1, id)
                .getSingleResult();
    }

    public List<Message> findByReceiver(Client receiver) {
        List <Message> messages = PersistanceUtil.getEntityManager().createNamedQuery("Message.findByReceiver")
                .setParameter(1, receiver).getResultList();
        return messages;
    }
}
