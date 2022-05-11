package app.persistanceUtil;

import app.entity.City;

import javax.persistence.PostPersist;
import javax.persistence.PrePersist;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TimeLogger {

    long startTime;

    @PrePersist
    public void Start(City city) {
       startTime = System.nanoTime();
    }

    @PostPersist
    public void logTime(City city) {
        long time = System.nanoTime() - startTime;
        try (FileWriter fileWriter = new FileWriter("./src/main/resources/log.txt", true);
             BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){
            bufferedWriter.append("Inserting time for the city " + city.getName() + " is " + time + " nanoseconds");
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
