package com.delieryplanner.serviceImpl;

import com.delieryplanner.model.Command;
import com.delieryplanner.model.Warehouse;
import com.delieryplanner.repository.CommandRepository;
import com.delieryplanner.repository.WarehouseRepository;
import com.delieryplanner.service.SolutionService;
import com.delieryplanner.util.SimulatedAnnealingTSP;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SolutionServiceImpl implements SolutionService {
    private final WarehouseRepository warehouseRepository ;
    private final CommandRepository commandRepository;
    @Override
    public Map<Long, List<Command>> resolveFor(Long warehouseId) {
        //o sa apelez algoritmul
        Warehouse warehouse = warehouseRepository.findById(warehouseId).get();
        List<Command> commands = commandRepository.findByWarehouse(warehouse);

        List<Command> solution;
        SimulatedAnnealingTSP simulatedAnnealingTSP = new SimulatedAnnealingTSP(commands, warehouse);

        Long idCar1=0L;
        Long idCar2=3L;

        Map<Long, List<Command>> result = new HashMap<>();
        Map<Double, List<Command>> bestRoute = simulatedAnnealingTSP.execute(10000);
        Double bestRouteCost = bestRoute.keySet().iterator().next();
        for (int count = 0; count < 5; count++){
            Map<Double, List<Command>> candidateRoute = simulatedAnnealingTSP.execute(1000);;
            Double candidateRouteCost = candidateRoute.keySet().iterator().next();
            if (candidateRouteCost < bestRouteCost){
                bestRouteCost = candidateRouteCost;
                bestRoute = candidateRoute;
            }

        }

        result.put(idCar1, bestRoute.get(bestRouteCost));

        return result;
    }

    @Override
    public Map<String, List<Double>> getLimitsFor(Long warehouseId) {
        java.util.List<Double> limits =  new ArrayList<>();
        limits.add(commandRepository.findMinimumLatitude(warehouseId));
        limits.add(commandRepository.findMinimumLongitude(warehouseId));
        limits.add(commandRepository.findMaximumLatitude(warehouseId));
        limits.add(commandRepository.findMaximumLongitude(warehouseId));
        Map<String, List<Double>> response = new HashMap<>();
        response.put("result", limits);
        return response;
    }
}
