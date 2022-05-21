package app.client;

import java.io.IOException;

public class TimeKeeper extends Thread {
    private final ClientThread clientThread;
    private long startTime = System.currentTimeMillis();

    public TimeKeeper(ClientThread clientThread){
        this.clientThread = clientThread;
    }

    public void extendTime(){
        startTime = System.currentTimeMillis();
    }

    @Override
    public void run() {
        long elapsedTime = 0L;

        int timeLimit = 30;
        while (elapsedTime < timeLimit * 1000) {
            elapsedTime = System.currentTimeMillis() - startTime;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }

        try {
            clientThread.getSocket().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
