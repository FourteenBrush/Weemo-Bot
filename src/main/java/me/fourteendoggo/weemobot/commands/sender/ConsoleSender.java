package me.fourteendoggo.weemobot.commands.sender;

import net.dv8tion.jda.api.Permission;

import java.util.logging.Logger;

public class ConsoleSender implements CommandSender {
    private static final Logger LOG = Logger.getLogger("Weemo");

    @Override
    public boolean hasPermission(Permission permission) {
        return true;
    }

    @Override
    public void sendMessage(String message) {
        LOG.info(message);
    }

    @Override
    public String toString() {
        return "ConsoleSender{}";
    }
}
