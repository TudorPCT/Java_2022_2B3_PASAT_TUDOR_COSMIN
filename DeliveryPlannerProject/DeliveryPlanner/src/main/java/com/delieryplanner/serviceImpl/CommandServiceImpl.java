package com.delieryplanner.serviceImpl;

import com.delieryplanner.model.Command;
import com.delieryplanner.model.Warehouse;
import com.delieryplanner.repository.CommandRepository;
import com.delieryplanner.repository.WarehouseRepository;
import com.delieryplanner.service.CommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor

public class CommandServiceImpl implements CommandService {

    private final CommandRepository commandRepository;
    private final WarehouseRepository warehouseRepository;

    @Override
    public Command addCommand(Command command) {
        return commandRepository.save(command);
    }

    @Override
    public List<Command> findAllCommands() {
        return commandRepository.findAll();
    }

    @Override
    public List<Command> findCommandsByWarehouse(Long warehouseId) {
        Warehouse warehouse=warehouseRepository.findById(warehouseId).get();
        return commandRepository.findByWarehouse(warehouse);
    }
}
