package com.company;

import com.company.app.MainFrame;

public class Main {
    public static void main(String[] args) {
        Main lab6 = new Main();
        lab6.compulsory();
        //lab5.homework();
    }

    public void compulsory(){
        new MainFrame().setVisible(true);
    }
}
