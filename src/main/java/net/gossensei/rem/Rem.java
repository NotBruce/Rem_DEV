package net.gossensei.rem;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.Compression;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import net.gossensei.rem.commands.WarnCommand;
import net.gossensei.rem.util.command.CommandManager;
import net.gossensei.rem.util.command.struct.Command;
import net.gossensei.rem.configuration.ConfigurationServices;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Rem {

    @Getter
    private static Rem instance;

    private JDA jda;
    public Map<String, Command> commandMap = new HashMap<>();

    public static void main(String[] args) throws LoginException, InterruptedException {
        new Rem().setup();
    }

    public void setup() throws LoginException, InterruptedException {
        instance = this;

        this.jda = JDABuilder.createDefault(new ConfigurationServices("config.json").getString("token"))
                .disableCache(CacheFlag.MEMBER_OVERRIDES, CacheFlag.VOICE_STATE)
                .addEventListeners(new CommandManager())
                .setBulkDeleteSplittingEnabled(false)
                .setCompression(Compression.NONE)
                .setActivity(Activity.watching("Hentai :)"))
                .enableIntents(GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_WEBHOOKS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_MESSAGE_TYPING, GatewayIntent.GUILD_MESSAGE_REACTIONS, GatewayIntent.GUILD_MEMBERS)
                .build().awaitReady();

        this.registerCommand();
    }
    public void registerCommand() {
        commandMap.put("warn", new WarnCommand());

        List<Guild> guilds = jda.getGuilds();
        System.out.println("Servers In: " + guilds.size());
        for (Guild guild : guilds) {
            for (Command command :  commandMap.values()) {
                System.out.println("[Slash-Command: Registered] " + command.getTitle());
                guild.upsertCommand(command.getTitle(), command.getDescription()).addOptions(command.getOptions()).setDefaultPermissions(DefaultMemberPermissions.enabledFor(command.getPermission())).queue();
            }
        }
    }
}
