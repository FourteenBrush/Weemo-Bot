package me.fourteendoggo.weemobot.commands.commands;

import me.fourteendoggo.weemobot.commands.DiscordCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class EchoCommand extends DiscordCommand {

    public EchoCommand() {
        super("echo", Permission.MODERATE_MEMBERS);
    }

    @Override
    public CommandResult execute(SlashCommandInteractionEvent event) {
        return null;
    }
}
