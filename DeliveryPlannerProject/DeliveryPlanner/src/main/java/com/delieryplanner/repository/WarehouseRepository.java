package com.delieryplanner.repository;

import com.delieryplanner.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    Warehouse findByTown(String town);
}
