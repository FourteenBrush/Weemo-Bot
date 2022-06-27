package me.fourteendoggo.weemobot;

import me.fourteendoggo.weemobot.commands.CommandListener;
import me.fourteendoggo.weemobot.commands.ConsoleListener;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.*;

public class WeemoBot {

    public static void main(String[] args) throws LoginException, InterruptedException, IOException, ParseException {
        new WeemoBot();
    }

    private WeemoBot() throws LoginException, InterruptedException, IOException, ParseException {
        Logger logger = initLogger();
        Config config = readConfig();

        JDA jda = JDABuilder.createDefault(config.token())
                .addEventListeners(new CommandListener(logger)).setActivity(Activity.watching("/help"))
                .build().awaitReady();

        Guild guild = jda.getGuildById(config.guildId());
        if (guild == null) {
            logger.warning("Didn't find the guild in the config.json, are you sure the bot is in there?");
            logger.warning("Disabling...");
            jda.shutdown();
            System.exit(1);
        }
        loadGuildCommands(guild);

        ConsoleListener consoleListener = new ConsoleListener(jda);
        consoleListener.setDaemon(true);
        consoleListener.start();

        logger.info("Started console listener thread");
        logger.info("Successfully enabled");
    }

    @SuppressWarnings("ConstantConditions")
    private Config readConfig() throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        JSONObject json;
        try (InputStreamReader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream("config.json"))) {
            json = (JSONObject) parser.parse(reader);
        }
        String token = (String) json.get("token");
        long guildId = (long) json.get("guildId");

        return new Config(token, guildId);
    }

    private Logger initLogger() {
        Logger logger = Logger.getLogger("Weemo");
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
        logger.addHandler(consoleHandler);
        logger.setUseParentHandlers(false);

        return logger;
    }

    private void loadGuildCommands(Guild guild) {
        guild.upsertCommand("hug", "give a hug to someone")
                .addOption(OptionType.STRING, "who", "who should get hugs", true)
                .addOptions(new OptionData(OptionType.INTEGER, "amount", "how many hugs")
                        .setRequiredRange(1, Short.MAX_VALUE)
                        .addChoice("1", 1))
                .queue();
    }

    private record Config(String token, long guildId) {}
}
