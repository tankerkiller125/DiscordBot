package ml.rhodes.discordbot;

import ml.rhodes.discordbot.commands.admin.Disconnect;
import ml.rhodes.discordbot.commands.admin.Reload;
import ml.rhodes.discordbot.util.ClassLoader;
import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.ReadyEvent;

import static ml.rhodes.discordbot.Core.discordClient;
class Ready implements IListener<ReadyEvent> {
    public void handle(ReadyEvent event) {
        System.out.println("The bot is connected");

        ClassLoader ClassLoader = new ClassLoader();
        ClassLoader.register();

        discordClient.getDispatcher().registerListener(new Logger());
        discordClient.getDispatcher().registerListener(new Disconnect());
        discordClient.getDispatcher().registerListener(new Reload());
        System.out.println("Listeners have been added");
    }
}
