package com.company.game;

import java.util.Date;

public class TimeKeeper implements Runnable {
    private int timeLimit = 100;
    private Game game;

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    @Override
    public void run() {
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0L;

        while (elapsedTime < timeLimit * 1000) {
            elapsedTime = (new Date()).getTime() - startTime;
            if(elapsedTime % (30_000) == 0)
                System.out.println("Elapsed time in seconds: " + elapsedTime / 1000);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        game.endGame();

    }
}
