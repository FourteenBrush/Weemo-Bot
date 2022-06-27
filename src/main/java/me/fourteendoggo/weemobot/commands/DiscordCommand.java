package me.fourteendoggo.weemobot.commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public abstract class DiscordCommand extends Command<SlashCommandInteractionEvent> {
    public static final Cooldown DEFAULT_COOLDOWN = new Cooldown(Duration.ofSeconds(3));
    private final List<Permission> requiredPermissions;

    public DiscordCommand(String name, Permission... requiredPermissions) {
        super(name, CommandType.GUILD_SENDER);
        this.requiredPermissions = switch (requiredPermissions.length) {
            case 0 -> Collections.emptyList();
            case 1 -> Collections.singletonList(requiredPermissions[0]);
            default -> Arrays.asList(requiredPermissions);
        };
    }

    public List<Permission> getRequiredPermissions() {
        return requiredPermissions;
    }
}
