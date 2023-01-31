package org.example.commands.music;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import org.example.commands.CommandContext;
import org.example.commands.ICommand;
import org.example.lavaplayer.PlayerManager;

import java.net.URI;
import java.net.URISyntaxException;

public class PlayLocalCommand implements ICommand {
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();

        if (ctx.getArgs().isEmpty()) {
            channel.sendMessage("Correct usage is `!!play <youtube link>`").queue();
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

        String trackName = String.join(" ", ctx.getArgs());

        trackName = "C:\\Users\\serega\\IdeaProjects\\KislotnyDJ\\src\\main\\resources\\" + trackName + ".mp3";

        PlayerManager.getInstance().loadAndPlay(channel, trackName);
    }

    @Override
    public String getName() {
        return "playlocal";
    }

    @Override
    public String getHelp() {
        return "Plays a song\n" +
                "Usage: `!!playlocal trackName`";
    }


}
