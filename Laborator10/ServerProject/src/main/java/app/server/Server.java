package app.server;

import app.client.ClientThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static final int PORT = 8100;

    public Server(){
        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println ("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                new ClientThread(socket, serverSocket).start();
            }
        } catch (IOException e) {
            System.err. println ("Ooops... " + e);
        }
    }
    public static void main ( String [] args ) throws IOException {
        Server server = new Server();
    }
}