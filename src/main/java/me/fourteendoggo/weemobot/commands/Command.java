package me.fourteendoggo.weemobot.commands;

import me.fourteendoggo.weemobot.commands.managers.CommandResult;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.util.Objects;

public abstract class Command {
    private final String name;
    private final String description;
    private final boolean guildOnly;
    private final int deleteMessageAfterSeconds;

    public Command(String name, String description, boolean guildOnly) {
        this (name, description, guildOnly, -1);
    }

    public Command(String name, String description, boolean guildOnly, int deleteMessageAfterSeconds) {
        this.name = name;
        this.description = description;
        this.guildOnly = guildOnly;
        this.deleteMessageAfterSeconds = deleteMessageAfterSeconds;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isGuildOnly() {
        return guildOnly;
    }

    public int getDeleteTime() {
        return deleteMessageAfterSeconds;
    }

    public abstract CommandResult execute(MessageReceivedEvent event, String[] args);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Command command = (Command) o;
        return guildOnly == command.guildOnly && deleteMessageAfterSeconds == command.deleteMessageAfterSeconds && name.equals(command.name) && description.equals(command.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, guildOnly, deleteMessageAfterSeconds);
    }
}
