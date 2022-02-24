package com.company;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Main lab = new Main();
       // lab.compulsory();
        lab.homework(args);
    }
    void compulsory() {
        //Step 1
        System.out.println("Hello World!");
        //Step 2
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        //Step 3
        int n = (int) (Math.random() * 1_000_000);
        //Step 4
        n*=3;
        n+=0b10101;
        n+=0xFF;
        n*=6;
        //Step 5
        int sum = 0; // Va memora suma cifrelor
        while(n / 10 != 0) {
            sum = 0;
            while (n != 0) {
                sum += n % 10;
                n /= 10;
            }
            n = sum;
        }
        //Step 6
        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
    }
    void homework(String[] args) {
        if (args.length < 3) {
            System.out.println("Input incorect. Format: n(numar), p(numar), alphabet(string)");
            System.exit(-1);
        }

        long startTime = System.nanoTime();
        int n = Integer.parseInt(args[0]);
        int p = Integer.parseInt(args[1]);
        int m = args[2].length();
        String[] array = new String[n];
        Random rand = new Random();

        StringBuilder alphabet = new StringBuilder();
        for(int i = 2; i < args.length; i++)
            alphabet.append(args[i].charAt(0));

        for(int i = 0; i < n; i++) {
            StringBuilder word = new StringBuilder();
            for (int j = 0; j < p; j++) {
                int k = rand.nextInt(alphabet.length());
                word.append(alphabet.charAt(k));
            }
            array[i] = word.toString();
            System.out.println("Cuvantul " + i + ": " + array[i]);
        }

        Boolean[][] matrix = new Boolean[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++){
                matrix[i][j] = matrix[j][i] = false;
                if(i == j)
                    continue;
                for(int t = 0; t < p; t++)
                    if(array[i].indexOf(array[j].charAt(t)) != -1) {
                        matrix[i][j] = matrix[j][i] = true;
                        break;
                    }
            }
        for(int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                System.out.print(matrix[i][j] + " ");
            System.out.println("");
        }


        long endTime = System.nanoTime();

        System.out.println("Timpul de rulare in nanosecunde: " + (endTime-startTime));
    }
    void bonus() {
//Do stuff
    }
}
