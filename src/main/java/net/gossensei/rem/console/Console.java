package net.gossensei.rem.console;

import jline.console.ConsoleReader;
import net.dv8tion.jda.api.JDA;
import net.gossensei.rem.console.commands.GuildCommand;
import net.gossensei.rem.console.commands.MessageCommand;
import net.gossensei.rem.console.commands.PrivateTextChannelCommand;
import net.gossensei.rem.console.struct.ConsoleCommand;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Console {
    public final String ANSI_RED = "\u001B[31m";
    public final String ANSI_BLUE = "\u001B[34m";
    public final String ANSI_PURPLE = "\u001B[35m";
    public final String ANSI_WHITE = "\u001B[37m";

    private final Map<String, ConsoleCommand> consoleCommands = new HashMap<>();

    public void setup(JDA jda) throws IOException {
        this.registerCommands();

        InputStream inStream = new FileInputStream(FileDescriptor.in);
        ConsoleReader reader = new ConsoleReader("Rem", inStream, System.out, null);

        reader.setPrompt(ANSI_WHITE + "♥" + ANSI_RED + " »" +  ANSI_BLUE + "»"  + ANSI_PURPLE + "»" + " ~ ");

        String input;

        while ((input = reader.readLine()) != null) {
            String command = input.split(" ")[0];
            if (consoleCommands.containsKey(command)) {
                consoleCommands.get(command).execute(input.split(" "), jda);
            }
        }

        // ensure that all content is written to the screen at the end to make unit tests stable
        reader.flush();
    }

    public void registerCommands() {
        consoleCommands.put("guild", new GuildCommand());
        consoleCommands.put("message", new MessageCommand());
        consoleCommands.put("private", new PrivateTextChannelCommand());
    }
}
