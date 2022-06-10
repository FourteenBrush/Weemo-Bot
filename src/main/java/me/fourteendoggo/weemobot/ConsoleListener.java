package me.fourteendoggo.weemobot;

import java.util.Scanner;

public class ConsoleListener implements Runnable {
    private final Bot bot;
    private final Scanner input;

    public ConsoleListener(Bot bot) {
        this.bot = bot;
        input = new Scanner(System.in);
    }

    @Override
    public void run() {
        while (true) {
            if (!input.hasNext()) continue;
            if ("stop".equals(input.next())) {
                bot.getLogger().info("Shutting down...");
                bot.getJda().shutdown();
                System.exit(0);
            } else {
                bot.getLogger().warning("Command not found");
            }
        }
    }
}
