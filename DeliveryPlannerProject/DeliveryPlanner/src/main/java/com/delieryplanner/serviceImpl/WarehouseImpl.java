package com.delieryplanner.serviceImpl;

import com.delieryplanner.model.Warehouse;
import com.delieryplanner.repository.WarehouseRepository;
import com.delieryplanner.service.WarehouseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseImpl implements WarehouseService {

    private final WarehouseRepository warehouseRepository;
    @Override
    public Warehouse addWarehouse(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @Override
    public List<Warehouse> findAllWarehouses() {
        return warehouseRepository.findAll();
    }


    @Override
    public Warehouse findWarehouseById(Long warehouseId) {
        return warehouseRepository.findById(warehouseId).get();
    }

    @Override
    public void delete(Long id) {
        warehouseRepository.deleteById(id);
    }

    @Override
    public Warehouse findWarehouseByTown(String warehouseTown) {
        return warehouseRepository.findByTown(warehouseTown);
    }

}
