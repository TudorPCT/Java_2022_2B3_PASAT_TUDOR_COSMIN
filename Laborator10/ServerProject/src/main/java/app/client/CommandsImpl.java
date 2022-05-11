package app.client;

import java.util.List;

public class CommandsImpl implements Commands{

    @Override
    public String executeCommand(String command) {
        return command;
    }

    @Override
    public Boolean register(String username, String password) {
        return null;
    }

    @Override
    public Boolean login(String username, String password) {
        return null;
    }

    @Override
    public String addFriends(List<String> friendsUsername) {
        return null;
    }

    @Override
    public Boolean sendMessage(String message) {
        return null;
    }

    @Override
    public String readMessage() {
        return null;
    }
}
