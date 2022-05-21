package app.server;

import app.client.entity.Client;
import app.client.ClientThread;
import app.client.repository.ClientRepository;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Server {

    public static final int PORT = 8100;
    public List<ClientThread> clientThreadList=new ArrayList<>();
    public Server(){

        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println ("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                ClientThread clientThread= new ClientThread(socket, serverSocket);
                clientThreadList.add(clientThread);
                clientThread.start();
                System.out.println(clientThread.getId()+" connected");
            }
        } catch (IOException e) {
            System.err. println ("Ooops... " + e);
        }
    }
    public static void main ( String [] args ) throws IOException {
        Server server = new Server();
    }
}