package net.gossensei.rem.console.struct;

import net.dv8tion.jda.api.JDA;

public interface ConsoleCommand {

    void execute(String[] args, JDA jda);
}
