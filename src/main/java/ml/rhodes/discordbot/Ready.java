package ml.rhodes.discordbot;

import ml.rhodes.discordbot.commands.*;
import ml.rhodes.discordbot.commands.github.RepoStats;
import sx.blah.discord.api.EventSubscriber;
import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;

import static ml.rhodes.discordbot.Core.discordClient;

public class Ready implements IListener<ReadyEvent> {
    @EventSubscriber
    public void handle(ReadyEvent event) {
        System.out.println("The bot is connected");

        // Admin Commands
        discordClient.getDispatcher().registerListener(new Logout());
        discordClient.getDispatcher().registerListener(new ChangeName());
        discordClient.getDispatcher().registerListener(new ChangeGame());
        discordClient.getDispatcher().registerListener(new ChangeAvatar());

        // Add Commands
        discordClient.getDispatcher().registerListener(new Help());
        discordClient.getDispatcher().registerListener(new Weather());
        discordClient.getDispatcher().registerListener(new Youtube());
        discordClient.getDispatcher().registerListener(new RepoStats());

        // Util Events
        discordClient.getDispatcher().registerListener(new Logger());
        discordClient.getDispatcher().registerListener(new Disconnect());

        System.out.println("Listeners have been added");
    }
}
