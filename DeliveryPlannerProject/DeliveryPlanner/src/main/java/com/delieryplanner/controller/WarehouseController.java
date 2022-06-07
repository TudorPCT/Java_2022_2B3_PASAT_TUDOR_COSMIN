package com.delieryplanner.controller;

import com.delieryplanner.model.Warehouse;
import com.delieryplanner.service.WarehouseService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;
    @PostMapping
    public ResponseEntity<?> addWarehouse(@RequestBody ObjectNode objectNode) {
        JsonNode town = objectNode.get("town");
        JsonNode latitude = objectNode.get("latitude");
        JsonNode longitude = objectNode.get("longitude");
        if (town == null || latitude == null || longitude == null)
            return ResponseEntity.badRequest().body("Need more data");
        return ResponseEntity.ok().body(warehouseService.addWarehouse(new Warehouse(town.asText(), latitude.asDouble(), longitude.asDouble())));
    }

    @GetMapping
    public ResponseEntity<?> getWarehouses(){
        return ResponseEntity.ok().body(warehouseService.findAllWarehouses());
    }

    @GetMapping("/{warehouse_town}")
    public ResponseEntity<?> getWarehouses(@PathVariable("warehouse_town") String warehouseTown){
        return ResponseEntity.ok().body(warehouseService.findWarehouseByTown(warehouseTown));
    }


//    @DeleteMapping
//    public void delete(@RequestBody ObjectNode objectNode){
//        Long id = objectNode.get("id").asLong();
//        warehouseService.delete(id);
//    }
}
