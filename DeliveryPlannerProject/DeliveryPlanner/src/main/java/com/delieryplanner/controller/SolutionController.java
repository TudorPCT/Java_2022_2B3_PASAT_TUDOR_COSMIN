package com.delieryplanner.controller;
import com.delieryplanner.service.WarehouseService;
import com.delieryplanner.service.SolutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/solution")
public class SolutionController {

   // private final OrderService orderService;
    private final SolutionService solutionService;
    private final WarehouseService warehouseService;

    @GetMapping("/{warehouse_id}")
    public ResponseEntity<?> getCommandsByWarehouse(@PathVariable("warehouse_id") Long warehouseId) {
        if (warehouseService.findWarehouseById(warehouseId) == null)
            return ResponseEntity.badRequest().body("id for warehouse invalid");
        return ResponseEntity.ok().body(solutionService.resolveFor(warehouseId));
    }

    @GetMapping("limits/{warehouse_id}")
    public ResponseEntity<?> getLimitsByWarehouse(@PathVariable("warehouse_id") Long warehouseId) {
        if (warehouseService.findWarehouseById(warehouseId) == null)
            return ResponseEntity.badRequest().body("id for warehouse invalid");
        return ResponseEntity.ok().body(solutionService.getLimitsFor(warehouseId));
    }
}