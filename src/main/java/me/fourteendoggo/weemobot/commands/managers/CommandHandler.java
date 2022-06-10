package me.fourteendoggo.weemobot.commands.managers;

import me.fourteendoggo.weemobot.commands.Command;
import me.fourteendoggo.weemobot.commands.cooldown.Cooldown;
import me.fourteendoggo.weemobot.commands.cooldown.CooldownManager;
import me.fourteendoggo.weemobot.utils.Lang;
import me.fourteendoggo.weemobot.utils.Utils;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CommandHandler extends ListenerAdapter {
    private static final String PREFIX = "?";

    private final Logger logger;
    private final Map<String, Command> commands;
    private final CooldownManager cooldownManager;

    public CommandHandler(Logger logger) {
        this.logger = logger;
        commands = new HashMap<>();
        cooldownManager = new CooldownManager();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        if (event.isWebhookMessage() || event.getAuthor().isBot()) return;
        String raw = event.getMessage().getContentRaw();
        if (!raw.startsWith(PREFIX)) return;

        String[] args = raw.substring(PREFIX.length()).split(" ");
        Command command = commands.get(args[0]);
        String guild = event.isFromGuild() ? event.getGuild().getName() : "PRIVATE MESSAGE";

        String log = Lang.COMMAND_SUCCESS.get(
                guild,
                event.getAuthor().getAsTag(),
                raw
        );
        if (command == null) {
            logger.info(Lang.COMMAND_NOT_FOUND.get(guild, event.getAuthor().getAsTag(), raw));
        } else if (command.isGuildOnly() && !event.isFromGuild()) {
            logger.info(Lang.COMMAND_NEEDS_GUILD.get(guild, event.getAuthor().getAsTag(), raw));
            event.getMessage().addReaction("❌").queue();
        } else if (cooldownManager.hasCooldown(event.getAuthor().getId())) {
            long timeLeft = cooldownManager.getCooldown(event.getAuthor().getId()).getTimeLeftMillis();
            String cooldown = Utils.millisToReadable(timeLeft);
            logger.info(Lang.COMMAND_COOLDOWN.get(guild, event.getAuthor().getAsTag(), raw, cooldown));
            Lang.PLAYER_COOLDOWN.replyTo(event.getMessage(), cooldown);
        } else try {
            logger.info(log);
            args = Arrays.copyOfRange(args, 1, args.length);
            CommandResult result = command.execute(event, args);
            switch (result) {
                case TOO_FEW_ARGS -> event.getMessage().reply(command.getDescription()).queue();
                case SUCCESS -> {
                    cooldownManager.addCooldown(event.getAuthor().getId(), new Cooldown(5, TimeUnit.SECONDS));
                    if (command.getDeleteTime() != -1) {
                        event.getMessage().delete().queueAfter(command.getDeleteTime(), TimeUnit.SECONDS);
                    }
                }
            }
        } catch (Exception e) {
            log = Lang.COMMAND_ERROR.get(guild, event.getAuthor().getAsTag(), raw);
            Lang.PLAYER_COMMAND_ERROR.replyTo(event.getMessage());
            logger.log(Level.SEVERE, log, e);
        }
    }

    public void addCommand(Command command) {
        commands.put(command.getName(), command);
    }
}
