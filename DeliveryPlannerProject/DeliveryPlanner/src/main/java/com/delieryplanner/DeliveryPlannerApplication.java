package com.delieryplanner;

import com.delieryplanner.util.Data;
import com.delieryplanner.util.SimulatedAnnealingTSP;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class DeliveryPlannerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeliveryPlannerApplication.class, args);
    }

    @Bean
    CommandLineRunner run(Data data){
        return args ->{
            data.getData("Iasi", "./src/main/resources/berlin52.tsp");
            data.getData("Bucuresti", "./src/main/resources/a280.tsp");
        };
    }

}
