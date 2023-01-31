package org.example.commands;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import org.example.commands.CommandContext;
import org.example.commands.ICommand;
import org.example.configs.StorageKeeper;

public class ShowTracksCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();

        channel.sendMessageFormat("All available tracks are: " + StorageKeeper.showAllAvailableTracks()).queue();
    }

    @Override
    public String getName() {
        return "showtracks";
    }

    @Override
    public String getHelp() {
        return "show all available local tracks\n" +
                "Usage: `!!showtracks`";
    }
}