package net.gossensei.rem.util.command;

import lombok.Data;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.gossensei.rem.Rem;

@Data
public class CommandManager extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (Rem.getInstance().commandMap.containsKey(event.getName())) {
            System.out.println("[Command Executed] " + event.getName() + " by " + event.getUser().getName());
            Rem.getInstance().commandMap.get(event.getName()).execute(event);
        }
    }
}
