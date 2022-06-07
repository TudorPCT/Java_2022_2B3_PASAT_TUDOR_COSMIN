package com.delieryplanner.repository;

import com.delieryplanner.model.Car;
import com.delieryplanner.model.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car,Long> {
    List<Car> findByWarehouse(Warehouse warehouse);
}
