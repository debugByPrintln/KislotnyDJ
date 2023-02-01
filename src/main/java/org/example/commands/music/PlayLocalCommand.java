package org.example.commands.music;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import org.example.commands.CommandContext;
import org.example.commands.ICommand;
import org.example.configs.StorageKeeper;
import org.example.lavaplayer.PlayerManager;

public class PlayLocalCommand implements ICommand {
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();

        if (ctx.getArgs().isEmpty()) {
            channel.sendMessage("Correct usage is `!!playlocal <track name>`").queue();
            return;
        }

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

        String trackName = String.join(" ", ctx.getArgs()) + ".mp3";

        if (!StorageKeeper.isInStorage(trackName)){
            channel.sendMessage("No such track in storage! Type '!!show' to see all available tracks.").queue();
            return;
        }

        trackName = "C:\\Users\\serega\\IdeaProjects\\KislotnyDJ\\src\\main\\resources\\" + trackName;

        PlayerManager.getInstance().loadAndPlay(channel, trackName);
    }

    @Override
    public String getName() {
        return "playlocal";
    }

    @Override
    public String getHelp() {
        return "Plays a song from a local storage\n" +
                "Usage: '!!playlocal <track_name>'";
    }


}
