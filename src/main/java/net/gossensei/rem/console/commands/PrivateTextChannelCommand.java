package net.gossensei.rem.console.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.PrivateChannel;
import net.gossensei.rem.console.struct.ConsoleCommand;

public class PrivateTextChannelCommand implements ConsoleCommand {
    @Override
    public void execute(String[] args, JDA jda) {
        if (args.length <= 2) {
            System.out.println("\u001B[31mCommand Details: private <user id> <message>");
            return;
        }

        String userId = args[1];

        PrivateChannel userPrivateChannel = jda.openPrivateChannelById(userId).complete();

        StringBuilder builder = new StringBuilder();
        for (int i = 2; i < args.length; i++) {
            builder.append(args[i]).append(" ");
        }

        String message = builder.toString();

        userPrivateChannel.sendMessage(message).queue();
    }
}
