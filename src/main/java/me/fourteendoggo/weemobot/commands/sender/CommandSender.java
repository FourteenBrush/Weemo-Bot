package me.fourteendoggo.weemobot.commands.sender;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;

public interface CommandSender {

    long getId();

    boolean hasPermission(Permission permission);

    default boolean hasPermission(Permission... permissions) {
        for (Permission permission : permissions) {
            if (!hasPermission(permission)) return false;
        }
        return true;
    }

    void sendMessage(String message);

    default void sendMessage(String... messages) {
        for (String message : messages) {
            sendMessage(message);
        }
    }

    static CommandSender fromMember(Member member) {

    }

    static CommandSender fromUser(User user) {

    }
}
