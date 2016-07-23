package ml.rhodes.discordbot.commands.admin;

import ml.rhodes.discordbot.util.ClassLoader;
import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import static ml.rhodes.discordbot.Core.config;
import static ml.rhodes.discordbot.Core.discordClient;

public class Reload implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        Boolean command = event.getMessage().getContent().startsWith("-reload");
        String channel = event.getMessage().getChannel().getID();

        if (command && event.getMessage().getAuthor().getID().equals(config.getString("discord.owner"))) {
            try {
                discordClient.getChannelByID(channel).sendMessage("**Reloading Commands**");
                ClassLoader ClassLoader = new ClassLoader();
                ClassLoader.unregister();
                ClassLoader.register();
                discordClient.getChannelByID(channel).sendMessage("**Commands Reloaded**");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
