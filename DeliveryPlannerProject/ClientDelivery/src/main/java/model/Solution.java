package model;

import controller.ClientController;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Solution {

    private Warehouse warehouse;
    private Map<Long,List<Node>> route;

    List<Double> limits;

    Double width;
    Double height;

    public Solution(String selectedTown){
        warehouse = ClientController.getWarehouse(selectedTown);
        route = ClientController.getSolution(warehouse);
        limits = ClientController.getLimits(warehouse);
        width = limits.get(2) - limits.get(0);
        height = limits.get(3) - limits.get(1);
    }



}
