package com.company;

import java.util.Random;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Main lab = new Main();
        lab.compulsory();
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
        int sum; // Va memora suma cifrelor
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

        long startTime = System.nanoTime(); //Inceperea cronometrului
        int n = Integer.parseInt(args[0]);
        int p = Integer.parseInt(args[1]);
        String[] array = new String[n];
        Random rand = new Random();

        StringBuilder alphabet = new StringBuilder(); //construim alfabetul din argumente
        for(int i = 2; i < args.length; i++) {
            alphabet.append(args[i].charAt(0));
        }

        for(int i = 0; i < n; i++) {
            StringBuilder word = new StringBuilder();
            for (int j = 0; j < p; j++) { //Construim cuvantul i folosind exact p litere alese random din alfabetul dat
                int k = rand.nextInt(alphabet.length());
                word.append(alphabet.charAt(k));
            }
            array[i] = word.toString(); //Adaugam noul cuvant in array
            System.out.println("Cuvantul " + i + ": " + array[i]);
        }

        Boolean[][] matrix = new Boolean[n][n]; //Matricea vecinilor
        for(int i = 0; i < n; i++)
            for(int j = i; j < n; j++){
                matrix[i][j] = matrix[j][i] = false; //Cum relatia de vecinatate merge in ambele sensuri, parcurgem matricea doar deasupra diagonalei principale
                if(i == j) //Un cuvant nu poate fi vecin cu el insusi
                    continue;
                for(int t = 0; t < p; t++) //Cautam fiecare litera din cuvantul j in cuvantul i si se opreste la prima gasita, cei doi fiind vecini
                    if(array[i].indexOf(array[j].charAt(t)) != -1) {
                        matrix[i][j] = matrix[j][i] = true;
                        break;
                    }
            }

        int[][] lists = new int[n][n];
        int[] listSize = new int[n];

        for(int i = 0; i < n; i++) { //Construim lista de adiacenta
            listSize[i] = 0;
            for (int j = 0; j < n; j++)
                if(matrix[i][j])
                    lists[i][listSize[i]++] = j;
        }

        for(int i = 0; i < n; i++) { //Afisam lista
            System.out.print("Vecinii cuvantului " + array[i] + " sunt: ");
            for (int j = 0; j < listSize[i]; j++)
                System.out.print(array[lists[i][j]] + " ");
            System.out.println();
        }


        long endTime = System.nanoTime(); //Sfarsitul cronometrului

        System.out.println("Timpul de rulare in nanosecunde: " + (endTime-startTime));

        bonus(array, lists, listSize);

    }

    void bonus(String[] array, int[][] lists, int[] listSize) {
        int[] maximum = new int[array.length+2];
        maximum[0] = -5;
        boolean first = false;
        for(int i = 0; i < array.length; i++)
        {
            int[] result = dfs(i,lists,listSize);
            if(result[0] > maximum[0])
                System.arraycopy(result,0, maximum,0,result.length);

            if(maximum[0] != -5 && !first)
            {
                System.out.print("Prima secventa: ");
                print(array,maximum);
                first = true;
            }

        }
        if(maximum[0] == -5)
            System.out.println("Nu s-a gasit nicio secventa de cuvinte");
        else {
            System.out.print("Secventa cu k maxim: ");
            print(array, maximum);
        }
    }

    void print(String[] array, int[] maximum)
    {
        int u = maximum[1];
        while(u != -1)
        {
            System.out.print(array[u] + " ");
            u = maximum[u+2];
        }
        System.out.println();
    }

    int[] dfs(int s, int[][] lists, int[] listSize)
    {
        int n = lists.length;
        int[] label = new int[n]; //Memoreaza pentru fiecare nod i ordinea sa in parcurgerea DFS
        int[] parent = new int[n]; //Memoreaza pentru fiecare nod parintele lui in parcurgerea DFS
        int[] next = new int[n];   //Memoreaza pentru fiecare nod, indicele din lista de adiacenta a primului vecin ce nu a fost ales deja
        int[] result = new int[n+2]; //Memoreaza solutia

        Arrays.fill(label,-2);
        Arrays.fill(parent,-2);
        Arrays.fill(next,0);

        label[s] = 0; //s este nodul de start
        parent[s] = -1;

        int[] myStack = new int[n]; //Stiva pentru parcurgerea DFS
        int last = 1;
        int ns = 0;
        int u; //Varful stivei

        myStack[0] = s; //Adaugam nodul de start in stiva
        result[0] = -5;

        while(last != 0)
        {
            u = myStack[last-1]; //Alegem nodul din varful stivei
            if(next[u] < listSize[u]) { //Alegem primul vecin disponibil din lista lui u
                if (label[lists[u][next[u]]] < -1) { //Daca nodul nu a fost vizitat il adaugam in stiva si modificam datele sale in cei doi vectori
                    ns++;
                    label[lists[u][next[u]]] = ns;
                    parent[lists[u][next[u]]] = u;
                    myStack[last++] = lists[u][next[u]];
                } else if (lists[u][next[u]] == s && parent[u] != s) //Daca vecinul ales este nodul de start,dar nu este parintele nodului u
                {                                                    //atunci se formeaza un circuit care este secventa ceruta
                    result[0] = label[u];
                    result[1] = u;
                    System.arraycopy(parent, 0, result, 2, n);
                }
                next[u]++;
            }
            else
                last--;
        }
        return result;
    }
}
