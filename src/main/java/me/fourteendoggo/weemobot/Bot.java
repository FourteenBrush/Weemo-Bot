package me.fourteendoggo.weemobot;

import me.fourteendoggo.weemobot.commands.EchoCommand;
import me.fourteendoggo.weemobot.commands.managers.CommandHandler;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import javax.security.auth.login.LoginException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class Bot {
    private final JDA jda;
    private final Logger logger;

    public static void main(String[] args) throws LoginException, InterruptedException {
        new Bot();
    }

    public Bot() throws LoginException, InterruptedException {
        this.logger = Logger.getLogger("Weemo bot");
        CommandHandler commandHandler = new CommandHandler(logger);
        commandHandler.addCommand(new EchoCommand());

        this.jda = JDABuilder.createDefault("ODY3MDMwMjk3OTMzNTEyNzM0.YPbK4w.TWbEg3R9S64cjNKLjjCgeisx7Rs")
                .addEventListeners(commandHandler).setActivity(Activity.watching("?help")).build().awaitReady();

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setFormatter(new Formatter() {
            private final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            @Override
            public String format(LogRecord record) { // [2022-03-16 18:01:30 INFO] Boo!
                return "[%s %s] %s\n".formatted(
                        formatter.format(new Date(record.getMillis())),
                        record.getLevel(),
                        record.getMessage()
                );
            }
        });
        logger.setUseParentHandlers(false);
        logger.addHandler(consoleHandler);
        new ConsoleListener(this).run();
        logger.info("Finished loading");
    }

    public JDA getJda() {
        return jda;
    }

    public Logger getLogger() {
        return logger;
    }
}
