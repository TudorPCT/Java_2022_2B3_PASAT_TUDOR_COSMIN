package com.delieryplanner.controller;

import com.delieryplanner.model.Command;
import com.delieryplanner.service.CommandService;
import com.delieryplanner.service.WarehouseService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/command")
public class CommandController {

    private final CommandService commandService;
    private final WarehouseService warehouseService;

    @PostMapping
    public ResponseEntity<?> addCommand(@RequestBody ObjectNode objectNode) {
        JsonNode latitude = objectNode.get("latitude");
        JsonNode longitude = objectNode.get("longitude");
        JsonNode capacity = objectNode.get("capacity");
        JsonNode warehouseId = objectNode.get("warehouse_id");
        if (warehouseId == null || capacity == null || latitude == null || longitude == null || warehouseService.findWarehouseById(warehouseId.asLong()) == null)
            return ResponseEntity.badRequest().body("Need more data or warehouse invalid");
        return ResponseEntity.ok().body(commandService.addCommand(new Command(latitude.asDouble(), longitude.asDouble(), capacity.asInt(), warehouseService.findWarehouseById(warehouseId.asLong()))));
    }

    @GetMapping
    public ResponseEntity<?> getAllCommands() {
        return ResponseEntity.ok().body(commandService.findAllCommands());
    }


    @GetMapping("/{warehouse_id}")
    public ResponseEntity<?> getCommandsByWarehouse(@PathVariable("warehouse_id") Long warehouseId) {
        if (warehouseService.findWarehouseById(warehouseId) == null)
            return ResponseEntity.badRequest().body("id for warehouse invalid");
        return ResponseEntity.ok().body(commandService.findCommandsByWarehouse(warehouseId));
    }
}