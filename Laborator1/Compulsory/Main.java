package com.company;

public class Main {

    public static void main(String[] args) {
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
}
