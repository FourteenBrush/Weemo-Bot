package me.fourteendoggo.weemobot.commands;

public abstract class Command<T> {
    private final String name;
    private final CommandType commandType;

    public Command(String name, CommandType commandType) {
        this.name = name;
        this.commandType = commandType;
    }

    public String getName() {
        return name;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public abstract CommandResult execute(T t);
}
