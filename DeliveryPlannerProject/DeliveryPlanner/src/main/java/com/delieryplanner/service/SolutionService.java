package com.delieryplanner.service;

import com.delieryplanner.model.Command;

import java.util.List;
import java.util.Map;

public interface SolutionService {
    Map<Long, List<Command>> resolveFor(Long warehouseId);

    Map<String, List<Double>> getLimitsFor(Long warehouseId);
}
