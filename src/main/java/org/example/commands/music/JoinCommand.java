package org.example.commands.music;

import net.dv8tion.jda.api.entities.GuildVoiceState;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.managers.AudioManager;
import org.example.commands.CommandContext;
import org.example.commands.ICommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SuppressWarnings("ConstantConditions")
public class JoinCommand implements ICommand {
    private static final Logger LOGGER = LoggerFactory.getLogger(JoinCommand.class);

    @Override
    public void handle(CommandContext ctx) {
        final TextChannel channel = ctx.getChannel();
        final Member self = ctx.getSelfMember();
        final GuildVoiceState selfVoiceState = self.getVoiceState();


        final Member member = ctx.getMember();
        final GuildVoiceState memberVoiceState = member.getVoiceState();

        if (!memberVoiceState.inVoiceChannel()) {
            LOGGER.info("!!join is called, but user is not in a voice channel");
            channel.sendMessage("You need to be in a voice channel for this command to work").queue();
            return;
        }

        final AudioManager audioManager = ctx.getGuild().getAudioManager();
        final VoiceChannel memberChannel = memberVoiceState.getChannel();

        if (selfVoiceState.inVoiceChannel()) {
            audioManager.closeAudioConnection();
            LOGGER.info("!!join is called, need to change the voice channel");
            channel.sendMessage("Leaving current voice channel. Type !!join again to join new voice channel").queue();
            return;
        }

        LOGGER.info("!!join is called by: " + ctx.getEvent().getAuthor().getName() + ". Guild name is: " + ctx.getGuild().getName());

        audioManager.openAudioConnection(memberChannel);
        channel.sendMessageFormat("Connecting to `\uD83D\uDD0A %s`", memberChannel.getName()).queue();
    }

    @Override
    public String getName() {
        return "join";
    }

    @Override
    public String getHelp() {
        return "Makes the bot join your voice channel";
    }
}
