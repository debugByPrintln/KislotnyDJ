package org.example.commands.music;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import org.example.commands.CommandContext;
import org.example.commands.ICommand;
import org.example.configs.StorageKeeper;
import org.example.lavaplayer.PlayerManager;
import org.example.listeners.Listener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PlayLocalCommand implements ICommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(PlayLocalCommand.class);

    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();



        if (ctx.getArgs().isEmpty()) {
            LOGGER.info("!!playlocal is called, but no argument was passed");
            channel.sendMessage("Correct usage is `!!playlocal <track_name>`").queue();
            return;
        }

        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();

        if (!selfVoiceState.inVoiceChannel()) {
            LOGGER.info("!!playlocal is called, but bot is not in voice channel");
            channel.sendMessage("I need to be in a voice channel for this to work").queue();
            return;
        }

        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inVoiceChannel()) {
            LOGGER.info("!!playlocal is called, but user is not in voice channel");
            channel.sendMessage("You need to be in a voice channel for this command to work").queue();
            return;
        }

        if (!memberVoiceState.getChannel().equals(selfVoiceState.getChannel())) {
            LOGGER.info("!!playlocal is called, but bot and user are in different voice channels");
            channel.sendMessage("You need to be in the same voice channel as me for this to work").queue();
            return;
        }

        String trackName = String.join(" ", ctx.getArgs()) + ".mp3";

        LOGGER.info("!!playlocal is called with: " + trackName + ". Called by: " + ctx.getEvent().getAuthor().getName());

        if (!StorageKeeper.isInStorage(trackName)){
            LOGGER.info("!!playlocal is unable to find track: " + trackName);
            channel.sendMessage("No such track in storage! Type '!!show' to see all available tracks.").queue();
            return;
        }

        trackName = "C:\\Users\\serega\\IdeaProjects\\KislotnyDJ\\src\\main\\resources\\music\\" + trackName;
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
