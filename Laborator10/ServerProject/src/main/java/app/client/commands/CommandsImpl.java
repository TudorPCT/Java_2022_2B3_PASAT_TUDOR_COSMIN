package app.client.commands;

import app.client.entity.Client;
import app.client.entity.Message;
import app.client.repository.ClientRepository;
import app.client.repository.MessageRepository;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class CommandsImpl implements Commands{

    private final ServerSocket serverSocket;
    private final PrintWriter out;
    private Client client = null;
    private final ClientRepository clientRepository;
    private final MessageRepository messageRepository;

    public CommandsImpl(Socket socket, ServerSocket serverSocket) throws IOException {
        this.serverSocket = serverSocket;
        this.out = new PrintWriter(socket.getOutputStream());
        this.clientRepository = new ClientRepository();
        this.messageRepository= new MessageRepository();
    }

    @Override
    public Boolean executeCommand(String command) throws IOException {

        String[] strings;

        if(command == null){
            return unkownCommand();
        }

        strings = command.split(" ");

        return switch (strings[0]) {
            case "stop" -> stop();
            case "exit" -> exit();
            case "register" -> register(strings[1]);
            case "login" -> login(strings[1]);
            case "logout" -> logout();
            case "friend" -> addFriends(Arrays.stream(strings).toList().subList(1, strings.length));
            case "seeMyFriends" ->seeMyFriends();
            case "send"->sendMessage(strings[1]);
            case "read"->readMessage();
            default -> unkownCommand();
        };
    }

    private Boolean seeMyFriends() {
        StringBuilder raspuns = new StringBuilder("Your friends: ");
        if(!isLogged())
        {
            raspuns = new StringBuilder("You're not logged");
        }
        else {
            List<Client> friends = this.client.getFriends();

            for (Client friend : friends)
            {
                raspuns.append(friend.getName());
                raspuns.append(' ');
            }
            System.out.println(raspuns);
        }
            out.println(raspuns);
            out.flush();
            return true;
    }

    public Boolean logout() {
        String raspuns = "Logout successful";
        if(this.client == null)
            raspuns = "Already logout";
        else
            this.client = null;
        out.println(raspuns);
        out.flush();
        return true;
    }

    @Override
    public Boolean register(String username) {
        Client client = new Client(username);
        String raspuns = "Register successful";
        if(isLogged())
            raspuns = "Already logged in";
        else {
            try {
                clientRepository.create(client);
            } catch (Exception e) {
                raspuns = "Error occurred when creating the account";
            }
        }
        out.println(raspuns);
        out.flush();
        return true;
    }
    public Boolean isLogged()
    {
        return this.client != null;
    }

    @Override
    public Boolean login(String username) {
        String raspuns = "Login successful";
        if(this.client != null)
            raspuns = "Already logged in";
        else
        {
            try {
                this.client = clientRepository.findByName(username);
            } catch (Exception e) {
                raspuns = "Error occurred at login: user not found";
            }
        }
        out.println(raspuns);
        out.flush();
        return true;
    }

    @Override
    public Boolean addFriends(List<String> friendsUsername) {
        String raspuns ="Friends added successfully";
        if(!isLogged())
        {
            raspuns = "You're not logged";
        }
        else
        {
            for (String friends:friendsUsername) {
                try {
                    Client friend = clientRepository.findByName(friends);
                    this.client.addFriend(friend);
                } catch (Exception e) {
                     raspuns = "Friend not found";
                    }
            }
            try {
                clientRepository.save(this.client);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        out.println(raspuns);
        out.flush();
        return true;
    }

    @Override
    public Boolean sendMessage(String message) {
        String raspuns = "Message send successfully";
        if(!isLogged())
        {
            raspuns = "You're not logged";
        }
        else
        {
            List<Client> friends = this.client.getFriends();
            for (Client friend : friends) {
                try {
                    messageRepository.create(new Message(this.client, friend, message));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        out.println(raspuns);
        out.flush();
        return true;
    }

    @Override
    public Boolean readMessage() {
        StringBuilder raspuns= new StringBuilder("Messages: ");
        if(!isLogged())
        {
            raspuns =new StringBuilder("You're not logged");
        }
        else
            {
                try {
                    List<Message> messages = messageRepository.findByReceiver(this.client);
                    for (Message message: messages) {
                        if(!message.isSeen())
                        {
                            raspuns.append("SENDER: ");
                            raspuns.append(message.getSender().getName());
                            raspuns.append(" -- TEXT: ");
                            raspuns.append(message.getText());
                            raspuns.append("    ");
                            message.setSeen(true);
                          messageRepository.save(message);
                        }
                    }
                    System.out.println(messages);
                }catch (Exception e) {
                    e.printStackTrace();
                }
        }
        out.println(raspuns);
        out.flush();
        return true;
    }

    @Override
    public Boolean unkownCommand() {
        String raspuns = "Unkown Command";
        out.println(raspuns);
        out.flush();
        return true;
    }

    @Override
    public Boolean stop() throws IOException {
        serverSocket.close();
        String raspuns = "Server stopped";
        out.println(raspuns);
        out.flush();
        return true;
    }

    @Override
    public Boolean exit() {
        String raspuns = "Exit";
        out.println(raspuns);
        return false;
    }
}
