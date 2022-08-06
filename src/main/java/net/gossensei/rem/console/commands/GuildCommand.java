package net.gossensei.rem.console.commands;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Guild;
import net.gossensei.rem.console.struct.ConsoleCommand;

public class GuildCommand implements ConsoleCommand {
    @Override
    public void execute(String[] args, JDA jda) {
        if (args.length == 1) {
            System.out.println("\u001B[31mNeed to specify an argument: list");
            return;
        }
        jda.getGuilds().forEach(guild -> {
            if (args[1].toLowerCase().equals(guild.getName().toLowerCase())) {
                if (args.length == 2) {
                    System.out.println("\u001B[31mPlease specify an option for this guild");
                    return;
                }

                if (args[2].toLowerCase().equals("leave")) {
                    System.out.println("\u001B[31mLeaving " + guild.getName());
                    guild.leave();
                    return;
                }

                if (args[2].toLowerCase().equals("info")) {
                    System.out.println("\u001B[34m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                    System.out.println("\u001B[34mGuild Name » \u001B[37m" + guild.getName());
                    System.out.println("\u001B[34mGuild Size » \u001B[37m" + guild.getMemberCount());
                    System.out.println("\u001B[34mGuild Owner » \u001B[37m" + guild.getOwner().getUser().getName());
                    System.out.println("\u001B[34m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                    return;
                }
                return;
            }
        });

        if (args[1].toLowerCase().equals("list")) {
            for (Guild guild : jda.getGuilds()) {
                System.out.println("\u001B[34m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                System.out.println("\u001B[34mGuild Name » \u001B[37m" + guild.getName());
                System.out.println("\u001B[34mGuild Size » \u001B[37m" + guild.getMemberCount());
                System.out.println("\u001B[34mGuild Owner » \u001B[37m" + guild.getOwner().getUser().getName());
                System.out.println("\u001B[34m━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                return;
            }
        }
    }
}
