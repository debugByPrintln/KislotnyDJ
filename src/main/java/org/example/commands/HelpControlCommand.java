package org.example.commands;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;

public class HelpControlCommand implements ICommand {
    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();

        channel.sendMessage("All available commands:" +
                "\n!!join - to join voice channel;" +
                "\n!!play <youtube_link> - to queue track from youtube;" +
                "\n!!nowplaying - to see, what's playing rn;" +
                "\n!!show - to see all available local tracks;" +
                "\n!!playlocal <track_name> - to play track from local file (if file is present);" +
                "\n!!repeat - to set current track on repeat;" +
                "\n!!queue - to see all queued tracks;" +
                "\n!!skip - to skip current track;" +
                "\n!!stop - to stop playing tracks and clear the queue;").queue();
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getHelp() {
        return "Shows all available tracks.\nUse: '!!help'";
    }
}
