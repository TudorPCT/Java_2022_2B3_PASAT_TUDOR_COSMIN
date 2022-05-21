package app.client.commands;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

public interface Commands {
    Boolean executeCommand(String command) throws IOException;
    Boolean register(String username);
    Boolean login(String username);
    Boolean logout();
    Boolean isLogged();
    Boolean addFriends(List<String> friendsUsername);
    Boolean sendMessage(String message);
    Boolean readMessage();
    Boolean unkownCommand();
    Boolean stop() throws IOException;
    Boolean exit();
}
