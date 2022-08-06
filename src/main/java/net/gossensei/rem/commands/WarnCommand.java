package net.gossensei.rem.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.gossensei.rem.util.command.struct.Command;

import java.awt.*;

public class WarnCommand implements Command {

    /**
     * Test Command - To see Command Manager
     * @return
     */

    @Override
    public String getTitle() {
        return "warn";
    }

    @Override
    public Permission getPermission() {
        return Permission.VOICE_MUTE_OTHERS;
    }

    @Override
    public String getDescription() {
        return "Warn Users";
    }

    @Override
    public OptionData[] getOptions() {
        return new OptionData[]
                {new OptionData(OptionType.USER, "user", "user to warn", true),
                new OptionData(OptionType.STRING, "reason", "reason to warn user", true)};
    }

    @Override
    public void execute(SlashCommandInteractionEvent event) {
        User reported = event.getOption("user").getAsUser();
        String reason = event.getOption("reason").getAsString();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setThumbnail(reported.getAvatarUrl());
        builder.setColor(new Color(235, 94, 181));
        builder.setTitle(reported.getAsTag() + " - Warned");
        builder.setFooter(reported.getAsTag() + " - Warned ➜ ID: " + reported.getId(), reported.getAvatarUrl());
        builder.setDescription("**Reason**: " + reason + "\n**Warned by**: " + event.getUser().getName());
        event.replyEmbeds(builder.build()).queue();
    }
}
