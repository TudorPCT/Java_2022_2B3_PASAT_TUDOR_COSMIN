package app.client;

import app.client.commands.Commands;
import app.client.commands.CommandsImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ClientThread extends Thread {

    private final Socket socket;
    private ServerSocket serverSocket;
    private TimeKeeper timeKeeper;

    public ClientThread(Socket socket, ServerSocket serverSocket) {
        this.socket = socket ;
        this.serverSocket = serverSocket;
        this.timeKeeper = new TimeKeeper(this);
    }

    public void run () {
        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
            String request = null;
            Commands commands = new CommandsImpl(socket, serverSocket);
            timeKeeper.start();
            while(true) {
                request = in.readLine();
                timeKeeper.extendTime();
                if(!commands.executeCommand(request)) {
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Communication error... " + e);
        } finally {
            try {
                System.out.println(this.getId()+" disconnected");
                socket.close();
            } catch (IOException e) { System.err.println (e); }
        }
    }

    public Socket getSocket() {
        return socket;
    }
}