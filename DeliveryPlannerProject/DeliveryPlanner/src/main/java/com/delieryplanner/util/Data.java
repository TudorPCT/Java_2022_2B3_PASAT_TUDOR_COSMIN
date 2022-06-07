package com.delieryplanner.util;

import com.delieryplanner.model.Command;
import com.delieryplanner.model.Warehouse;
import com.delieryplanner.repository.CommandRepository;
import com.delieryplanner.repository.WarehouseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


@Service
@RequiredArgsConstructor
@Slf4j
public class Data {

    private final WarehouseRepository warehouseRepository;
    private final CommandRepository commandRepository;

    public void getData(String warehouseCity, String path) throws FileNotFoundException {

        Random rand = new Random();
        Warehouse warehouse = null;

        File file = new File(path);
        Scanner reader = new Scanner(file);
        boolean begin = false;
        while (reader.hasNextLine()) {
            String line = reader.nextLine();

            if (line.equals("NODE_COORD_SECTION")){
                begin = true;
                continue;
            }
            else if (Boolean.FALSE.equals(begin) || line.equals("EOF"))
                continue;

            String[] lineData = line.trim().split(" ", -1);
            String[] pointData = new String[3];
            int index = 0;
            for(String data : lineData){
                if (!data.equals(""))
                    pointData[index++] = data;
            }

            if (pointData[0].equals("1")) {
                warehouse = new Warehouse(warehouseCity, Double.parseDouble(pointData[1]), Double.parseDouble(pointData[2]));
                warehouseRepository.save(warehouse);
            }else
                commandRepository.save(new Command(Double.parseDouble(pointData[1]), Double.parseDouble(pointData[2]), rand.nextInt(0, 50), warehouse));
        }
        reader.close();
    }

}

