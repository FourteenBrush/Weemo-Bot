package me.fourteendoggo.weemobot.commands;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

public class CommandListener extends ListenerAdapter {
    private static final Logger LOG = Logger.getLogger("Weemo");

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

    }
}
