package main;

import controller.ClientController;
import model.Node;
import model.Warehouse;
import paint.MainFrame;

import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        new MainFrame().setVisible(true);
    }
}
