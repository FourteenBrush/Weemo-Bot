package me.fourteendoggo.weemobot.commands;

import me.fourteendoggo.weemobot.commands.managers.CommandResult;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class EchoCommand extends Command {

    public EchoCommand() {
        super("echo", "echoes a message to the current channel", true);
    }

    @Override
    public CommandResult execute(MessageReceivedEvent event, String[] args) {
        if (args.length == 0) return CommandResult.TOO_FEW_ARGS;
        if (args.length == 1) {
            event.getChannel().sendMessage(args[0]).queue();
        } else {
            StringBuilder builder = new StringBuilder();
            for (String str : args) {
                builder.append(str).append(" ");
            }
            event.getChannel().sendMessage(builder.toString()).queue();
        }
        return CommandResult.SUCCESS;
    }
}
