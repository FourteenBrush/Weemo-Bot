package me.fourteendoggo.weemobot.commands.commands;

import me.fourteendoggo.weemobot.commands.DiscordCommand;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

public class EchoCommand extends DiscordCommand {

    public EchoCommand() {
        super("echo", CommandType.GUILD_COMMAND, Permission.MODERATE_MEMBERS);
    }

    @Override
    @SuppressWarnings("ConstantConditions")
    public CommandResult execute(SlashCommandInteractionEvent event) {
        OptionMapping channelOption = event.getOption("channel");
        TextChannel channel = channelOption != null ? channelOption.getAsTextChannel() : event.getTextChannel();

        channel.sendMessage(event.getOption("message").getAsString()).queue();
        return CommandResult.SUCCESS;
    }
}
