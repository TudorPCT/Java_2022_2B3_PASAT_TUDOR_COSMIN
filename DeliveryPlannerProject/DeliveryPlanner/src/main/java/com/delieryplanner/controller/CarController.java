package com.delieryplanner.controller;


import com.delieryplanner.model.Car;
import com.delieryplanner.service.CarService;
import com.delieryplanner.service.WarehouseService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService carService;
    private final WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<?> addCar(@RequestBody ObjectNode objectNode) {
        JsonNode warehouseId = objectNode.get("warehouse_id");
        JsonNode capacity = objectNode.get("capacity");
        if (warehouseId == null || capacity == null || warehouseService.findWarehouseById(warehouseId.asLong())==null )
            return ResponseEntity.badRequest().body("Need more data or warehouse invalid");
        return ResponseEntity.ok().body(carService.addCar(new Car(capacity.asInt(), warehouseService.findWarehouseById(warehouseId.asLong()))));
    }

    @GetMapping
    public ResponseEntity<?> getAllCars(){
        return ResponseEntity.ok().body(carService.getAllCars());
    }


    @GetMapping("/{warehouse_id}")
    public ResponseEntity<?> getCarsByWarehouse(@PathVariable("warehouse_id") Long warehouseId)
    {
        if(warehouseService.findWarehouseById(warehouseId)==null)
            return ResponseEntity.badRequest().body("id for warehouse invalid");
        return ResponseEntity.ok().body(carService.getCarsByWarehouses(warehouseId));
    }

//    @DeleteMapping
//    public void delete(@RequestBody ObjectNode objectNode){
//        Long id = objectNode.get("id").asLong();
//        carService.delete(id);
//    }
}
