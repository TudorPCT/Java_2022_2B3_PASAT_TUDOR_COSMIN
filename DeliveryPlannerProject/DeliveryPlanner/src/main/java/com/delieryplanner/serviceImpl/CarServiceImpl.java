package com.delieryplanner.serviceImpl;

import com.delieryplanner.model.Car;
import com.delieryplanner.model.Warehouse;
import com.delieryplanner.repository.CarRepository;
import com.delieryplanner.repository.WarehouseRepository;
import com.delieryplanner.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final WarehouseRepository warehouseRepository;

    @Override
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    @Override
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    @Override
    public List<Car> getCarsByWarehouses(Long warehouseId) {
        Warehouse warehouse=warehouseRepository.findById(warehouseId).get();
        return carRepository.findByWarehouse(warehouse);
    }

    @Override
    public void delete(Long id) {
        warehouseRepository.deleteById(id);
    }
}
