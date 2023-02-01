package org.example.commands.music;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import org.example.commands.CommandContext;
import org.example.commands.ICommand;
import org.example.configs.StorageKeeper;

public class ShowTracksCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();

        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inVoiceChannel()) {
            channel.sendMessage("I need to be in a voice channel for this to work").queue();
            return;
        }

        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inVoiceChannel()) {
            channel.sendMessage("You need to be in a voice channel for this command to work").queue();
            return;
        }

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            channel.sendMessage("You need to be in the same voice channel as me for this to work").queue();
            return;
        }

        channel.sendMessageFormat("All available local tracks are: " + StorageKeeper.showAllAvailableTracks() + "\nTo play local track use '!!playlocal <track_name>'").queue();
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getHelp() {
        return "Shows all available local tracks\n" +
                "Usage: '!!show'";
    }
}