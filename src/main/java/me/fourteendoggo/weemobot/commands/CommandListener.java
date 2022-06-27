package me.fourteendoggo.weemobot.commands;

import me.fourteendoggo.weemobot.commands.sender.CommandSender;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class CommandListener extends ListenerAdapter {
    private static final Logger LOG = Logger.getLogger("Weemo");
    private final Map<String, CommandEntry> commandMap = new HashMap<>();

    @Override
    @SuppressWarnings("ConstantConditions")
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        CommandEntry entry = commandMap.get(event.getName());
        if (entry == null) return;
        DiscordCommand command = entry.command();

        CommandSender sender;
        if (event.isGuildCommand()) {
            sender = CommandSender.fromMember(event.getMember());
        } else {
            sender = CommandSender.fromUser(event.getUser());
        }

        if (!sender.hasPermission(command.getRequiredPermissions())) {
            sender.sendMessage("Insufficient permissions");
            return;
        }

        CooldownManager cooldownManager = entry.cooldownManager();
        if


        if (event.getName().equals("hug")) {
            String who = event.getOption("who").getAsString();

            OptionMapping option = event.getOption("amount");
            int amount = option != null ? option.getAsInt() : 1;

            event.reply("Gave %s %s to %s".formatted(amount, amount > 1 ? "hugs" : "hug", who)).queue();
            if (Math.random() < 0.5) {
                event.getChannel().sendMessage("Did ya bitches run away or smth?").queue();
            } else {
                event.getChannel().sendMessage("Hmm they have alot of bitches :)").queue();
            }
        }
    }

    private record CommandEntry(DiscordCommand command, CooldownManager cooldownManager) {}
}
