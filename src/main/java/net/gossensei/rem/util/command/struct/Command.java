package net.gossensei.rem.util.command.struct;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

public interface Command {

    String getTitle();

    Permission getPermission();

    String getDescription();

    OptionData[] getOptions();

    void execute(SlashCommandInteractionEvent event);

}
