package me.fourteendoggo.weemobot.commands;

public abstract class Command<T> {
    private final String name;
    private final CommandType commandType;

    public Command(String name, CommandType commandType) {
        this.name = name;
        this.commandType = commandType;
    }

    public abstract CommandResult execute(T t);

    protected enum CommandType {
        CONSOLE,
        GUILD_SENDER,
        DM_SENDER
    }

    protected enum CommandResult {
        SUCCESS,
        FAILURE
    }
}
