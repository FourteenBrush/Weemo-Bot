package me.fourteendoggo.weemobot.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public abstract class DiscordCommand extends Command<SlashCommandInteractionEvent> {
    public static final Cooldown DEFAULT_COOLDOWN = new Cooldown(Duration.ofSeconds(3));
    private final Map<Long, Cooldown> cooldownMap = new HashMap<>();
    private final Permission[] requiredPermissions;

    public DiscordCommand(String name, CommandType commandType, Permission... requiredPermissions) {
        super(name, commandType);
        this.requiredPermissions = requiredPermissions;
    }

    public Permission[] getRequiredPermissions() {
        return requiredPermissions;
    }
}
