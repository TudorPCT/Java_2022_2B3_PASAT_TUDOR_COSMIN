package app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.util.Scanner;

public class Client {

    private static final String serverAddress = "127.0.0.1";
    private static final int PORT = 8100;

    public Client(){
        try (Socket socket = new Socket(serverAddress, PORT);
             PrintWriter out =
                     new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader (
                     new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            while(true) {
                System.out.print("Command: ");
                String request = scanner.nextLine();
                out.println(request);
                String response = in.readLine();
                System.out.println(response);

                if (response.equals("Exit")) {
                    socket.close();
                    break;
                }
            }
        }catch (Exception e) {
            System.err.println("No server listening... " + e);
        }

    }

    public static void main (String[] args) {
        new Client();
    }
}

