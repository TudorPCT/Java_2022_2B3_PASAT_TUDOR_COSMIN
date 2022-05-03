package app.persistanceUtil;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistanceUtil {
    private static EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("default");

    private PersistanceUtil(){}
    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}
