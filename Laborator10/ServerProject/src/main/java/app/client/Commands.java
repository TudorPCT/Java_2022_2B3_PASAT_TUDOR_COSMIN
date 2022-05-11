package app.client;

import java.util.List;

public interface Commands {
    String executeCommand(String command);
    Boolean register(String username, String password);
    Boolean login(String username, String password);
    String addFriends(List<String> friendsUsername);
    Boolean sendMessage(String message);
    String readMessage();
}
