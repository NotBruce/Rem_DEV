package net.gossensei.rem.console.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.gossensei.rem.console.struct.ConsoleCommand;

public class MessageCommand implements ConsoleCommand {

    @Override
    public void execute(String[] args, JDA jda) {
        if (args.length <= 5) {
            System.out.println("\u001B[31mCommand Details: message <guild> <channel id> <message>");
            return;
        }

        String guildName = args[1];
        String channelId = args[2];

        Guild guildFound = null;

        StringBuilder builder = new StringBuilder();
        for (int i = 3; i < args.length; i++) {
            builder.append(args[i]).append(" ");
        }
        String message = builder.toString();

        jda.getGuildsByName(guildName, true).get(0).getTextChannelById(channelId).sendMessage(message).queue();

    }
}
