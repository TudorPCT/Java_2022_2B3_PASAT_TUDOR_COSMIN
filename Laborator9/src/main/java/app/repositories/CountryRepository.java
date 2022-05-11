package app.repositories;


import app.entity.Country;
import app.persistanceUtil.PersistanceUtil;

import java.util.List;


public class CountryRepository  extends DataRepository<Country,Integer>{

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
