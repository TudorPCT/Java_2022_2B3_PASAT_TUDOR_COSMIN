package com.delieryplanner.service;

import com.delieryplanner.model.Car;
import com.delieryplanner.model.Warehouse;

import java.util.List;

public interface CarService {
    Car addCar(Car car);
    List<Car> getAllCars();
    List<Car> getCarsByWarehouses(Long warehouseId);

    void delete(Long id);
}
