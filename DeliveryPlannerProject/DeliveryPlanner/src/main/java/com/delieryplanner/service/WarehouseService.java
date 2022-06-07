package com.delieryplanner.service;

import com.delieryplanner.model.Warehouse;

import java.util.List;

public interface WarehouseService {
    Warehouse addWarehouse(Warehouse warehouse);
    List<Warehouse> findAllWarehouses();
    Warehouse findWarehouseById(Long warehouseId);

    void delete(Long id);

    Warehouse findWarehouseByTown(String warehouseTown);
}
