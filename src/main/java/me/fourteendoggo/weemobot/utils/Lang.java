package me.fourteendoggo.weemobot.utils;

import net.dv8tion.jda.api.entities.Message;

public enum Lang {
    COMMAND_SUCCESS(" [%s] Received command from user %s: %s"),
    COMMAND_NOT_FOUND(" [%s] Received unknown command from user %s: %s"),
    COMMAND_NEEDS_GUILD(COMMAND_SUCCESS.message + ", which got declined due to it being a guild-only command which is used in a private chat"),
    COMMAND_COOLDOWN(COMMAND_SUCCESS.message + ", which got declined due to the user still having a cooldown of %s"),
    PLAYER_COOLDOWN("Please wait %s before using that command again"),
    COMMAND_ERROR("[%s] An exception occurred whilst executing a command, user: %s: %s"),
    PLAYER_COMMAND_ERROR("Something went wrong while executing that command")
    ;

    private final String message;
    private final boolean hasPlaceHolders;

    Lang(String message) {
        this.message = message;
        this.hasPlaceHolders = message.contains("%s");
    }

    public String get(String... args) {
        return hasPlaceHolders ? message.formatted((Object[]) args) : message;
    }
}
