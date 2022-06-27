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
        CONSOLE_COMMAND,
        GUILD_COMMAND,
        PRIVATE_COMMAND
    }

    protected enum CommandResult {
        SUCCESS,
        FAILURE
    }
}
