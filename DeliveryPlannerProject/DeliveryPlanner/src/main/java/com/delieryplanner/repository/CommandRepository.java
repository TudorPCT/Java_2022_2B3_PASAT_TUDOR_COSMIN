package com.delieryplanner.repository;

import com.delieryplanner.model.Command;
import com.delieryplanner.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommandRepository extends JpaRepository<Command,Long> {

    List<Command> findByWarehouse(Warehouse warehouse);

    @Query(value = "select min(latitude) from command where warehouse_id = ?1",
            nativeQuery = true)
    Double findMinimumLatitude(Long warehouseId);

    @Query(value = "select min(longitude) from command where warehouse_id = ?1",
            nativeQuery = true)
    Double findMinimumLongitude(Long warehouseId);

    @Query(value = "select max(latitude) from command where warehouse_id = ?1",
            nativeQuery = true)
    Double findMaximumLatitude(Long warehouseId);

    @Query(value = "select max(longitude) from command where warehouse_id = ?1",
            nativeQuery = true)
    Double findMaximumLongitude(Long warehouseId);
}
