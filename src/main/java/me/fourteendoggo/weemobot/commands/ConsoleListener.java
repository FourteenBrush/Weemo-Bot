package me.fourteendoggo.weemobot.commands;

import net.dv8tion.jda.api.JDA;

import java.util.Scanner;
import java.util.logging.Logger;

public class ConsoleListener extends Thread {
    private static final Logger LOG = Logger.getLogger("Weemo");
    private final JDA jda;

    public ConsoleListener(JDA jda) {
        super("Weemo console listener thread");
        this.jda = jda;
    }

    @Override
    public void run() {
        Scanner in = new Scanner(System.in);
        while (true) {
            switch (in.next().toLowerCase()) {
                case "stop" -> {
                    LOG.info("Shutting down...");
                    jda.shutdown();
                    System.exit(0);
                }
                case "version", "ver" -> LOG.info("Running Weemo v1.0 by FourteenDoggo");
                case "help" -> LOG.info("available commands: version, help, stop");
                default -> LOG.warning("Command not found, try help for a list of commands");
            }
        }
    }
}
