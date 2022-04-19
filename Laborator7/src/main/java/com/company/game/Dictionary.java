package com.company.game;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Dictionary {

    private Set<String> dictionaryWords = new HashSet<>();

    public Dictionary(){
        try (BufferedReader reader = new BufferedReader(new FileReader("./src/main/resources/words.txt"))) {
            String line = reader.readLine();
            while (line != null) {
                dictionaryWords.add(line.toUpperCase());
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isWord(String str) {return dictionaryWords.contains(str);
    }

}
