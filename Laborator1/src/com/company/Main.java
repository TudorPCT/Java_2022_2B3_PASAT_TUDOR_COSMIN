package com.company;

import java.util.Random;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        Main lab = new Main();
        lab.compulsory();
        lab.homework(args);

    }
    void compulsory() {
        System.out.println("Hello World!");
        String[] languages = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        n*=3;
        n+=0b10101;
        n+=0xFF;
        n*=6;
        int sum;
        while(n / 10 != 0) {
            sum = 0;
            while (n != 0) {
                sum += n % 10;
                n /= 10;
            }
            n = sum;
        }
        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);
    }

    void homework(String[] args) {
        String errPrint = "Input incorect. Format: n(numar), p(numar), alphabet(string)";
        if (args.length < 3) {
            System.out.println(errPrint);
            System.exit(-1);
        }

        long startTime = System.nanoTime();

        int n = 0;
        int p = 0;
        if(Pattern.matches("[0-9]+", args[0]))
            n = Integer.parseInt(args[0]);
        else{
            System.out.println(errPrint);
            System.exit(-1);
        }
        if(Pattern.matches("[0-9]+", args[1]))
            p = Integer.parseInt(args[1]);
        else{
            System.out.println(errPrint);
            System.exit(-1);
        }

        String[] array = new String[n];
        Random rand = new Random();

        StringBuilder alphabet = new StringBuilder();
        for(int i = 2; i < args.length; i++) {
            if(args[i].length() > 1 || !Pattern.matches("[a-zA-Z]", args[i])) {
                System.out.println(errPrint);
                System.exit(-1);
            }
                alphabet.append(args[i].charAt(0));
        }

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
            for(int j = i; j < n; j++){
                matrix[i][j] = matrix[j][i] = false;
                if(i == j)
                    continue;
                for(int t = 0; t < p; t++)
                    if(array[i].indexOf(array[j].charAt(t)) != -1) {
                        matrix[i][j] = matrix[j][i] = true;
                        break;
                    }
            }

        int[][] lists = new int[n][n];
        int[] listSize = new int[n];

        for(int i = 0; i < n; i++) {
            listSize[i] = 0;
            for (int j = 0; j < n; j++)
                if(Boolean.TRUE.equals(matrix[i][j]))
                    lists[i][listSize[i]++] = j;
        }

        for(int i = 0; i < n; i++) {
            System.out.print("Vecinii cuvantului " + array[i] + " sunt: ");
            for (int j = 0; j < listSize[i]; j++)
                System.out.print(array[lists[i][j]] + " ");
            System.out.println();
        }


        long endTime = System.nanoTime();

        System.out.println("Timpul de rulare in nanosecunde: " + (endTime-startTime));

        bonus(array, lists, listSize);

    }

    void bonus(String[] array, int[][] lists, int[] listSize) {
        dfs(0,array,lists,listSize);
    }

    void print(String[] array, int[] parent, int u, int v)
    {
        while(u != v){
            System.out.print(array[u] + " ");
            u = parent[u];
        }
        System.out.println(array[v]);
    }

    void dfs(int s, String[] array, int[][] lists, int[] listSize)
    {
        int n = lists.length;
        int[] label = new int[n];
        int[] parent = new int[n];
        int[] next = new int[n];

        Arrays.fill(label,-2);
        Arrays.fill(parent,-2);
        Arrays.fill(next,0);

        label[s] = 0;
        parent[s] = -1;

        int[] myStack = new int[n];
        int last = 1;
        int ns = 0;
        int u;

        myStack[0] = s;
        while(last != 0){
            u = myStack[last-1];
            if(next[u] < listSize[u]) {
                if (label[lists[u][next[u]]] < -1) {
                    ns++;
                    label[lists[u][next[u]]] = ns;
                    parent[lists[u][next[u]]] = u;
                    myStack[last++] = lists[u][next[u]];
                } else if (lists[u][next[u]] != parent[u]){
                    System.out.print("Prima secventa de cuvinte gasita: ");
                    print(array, parent, u, lists[u][next[u]]);
                    return;
                }
                next[u]++;
            }
            else
                last--;
        }
        System.out.println("Nu s-a gasit secventa ceruata");
    }
}
