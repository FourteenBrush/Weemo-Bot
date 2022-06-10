package me.fourteendoggo.weemobot.utils;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

public enum Lang {
    COMMAND_SUCCESS(" [{0}] Received command from user {1}: {2}"),
    COMMAND_NOT_FOUND(" [{0}] Received unknown command from user {1}: {2}"),
    COMMAND_NEEDS_GUILD(COMMAND_SUCCESS.message + ", which got declined due to it being a guild-only command which is used in a private chat"),
    COMMAND_COOLDOWN(COMMAND_SUCCESS.message + ", which got declined due to the user still having a cooldown of {3}"),
    PLAYER_COOLDOWN("Please wait {0} before using that command again"),
    COMMAND_ERROR("[{0}] An exception occurred whilst executing a command, user: {1}: {2}"),
    PLAYER_COMMAND_ERROR("Something went wrong while executing that command")
    ;

    private final String message;

    Lang(String message) {
        this.message = message;
    }

    public String get(String... args) {
        if (args.length == 0) return message;
        String output = message;
        for (int i = 0; i < args.length; i++) {
            output = output.replace("{" + i + "}", args[i]);
        }
        return output;
    }

    public void sendTo(TextChannel channel, String... args) {
        channel.sendMessage(get(args)).queue();
    }

    public void replyTo(Message message, String... args) {
        message.reply(get(args)).queue();
    }
}
