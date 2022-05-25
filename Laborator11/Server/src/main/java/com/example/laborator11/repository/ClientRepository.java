package com.example.laborator11.repository;

import com.example.laborator11.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByName(String name);

//    @Query(value = "select * from client c where c.id in" +
//            "(select id_friend1 from relations group by id_friend1 having count(id_friend1) = " +
//            "(select max(nr) from (select count(id_friend1) as nr from relations group by id_friend1) as maximum))" +
//            "limit :k",
//            nativeQuery = true)
//    List<Client> findMostPopularK(@Param("k") Integer k);
}
