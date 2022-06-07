package com.delieryplanner.service;

import com.delieryplanner.model.Command;

import java.util.List;

public interface CommandService {
    Command addCommand(Command command);
    List<Command> findAllCommands();
    List<Command> findCommandsByWarehouse(Long warehouseId);
}
