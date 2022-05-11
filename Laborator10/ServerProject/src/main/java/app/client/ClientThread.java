package app.client;

import app.server.Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread extends Thread {

    private final Socket socket;
    private final Commands commands = new CommandsImpl();
    public ServerSocket serverSocket;

    public ClientThread(Socket socket, ServerSocket serverSocket) {
        this.socket = socket ;
        this.serverSocket = serverSocket;
    }

    public void run () {
        try {
            BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));
            String request = null;
            PrintWriter out = new PrintWriter(socket.getOutputStream());

            while(true) {
                request = in.readLine();
                if (request == null) {
                    System.out.println("exit");
                    break;
                } else if (request.equals("stop")) {
                    serverSocket.close();
                    String raspuns = "Server stopped";
                    out.println(raspuns);
                    out.flush();
                } else {
                    String raspuns = "Server received the request: " + request + "!";
                    out.println(raspuns);
                    commands.executeCommand(request);
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                socket.close();
            } catch (IOException e) { System.err.println (e); }
        } }
}