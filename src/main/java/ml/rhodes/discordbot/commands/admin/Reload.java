package ml.rhodes.discordbot.commands.admin;

import com.typesafe.config.ConfigFactory;
import ml.rhodes.discordbot.util.ClassLoader;
import ml.rhodes.discordbot.util.User;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import static ml.rhodes.discordbot.Core.discordClient;

public class Reload implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        Boolean command = event.getMessage().getContent().startsWith("-reload");
        String channel = event.getMessage().getChannel().getID();
        String userID = event.getMessage().getAuthor().getID();

        if (command && (User.isOwner(userID) || User.isModerator(userID))) {
            try {
                ConfigFactory.invalidateCaches();
                discordClient.getChannelByID(channel).sendMessage("<@!" + userID + "> Initiated Reload");
                discordClient.getChannelByID(channel).sendMessage("**Reloading Commands & Config**");
                ClassLoader ClassLoader = new ClassLoader();
                ClassLoader.unregister();
                ClassLoader.register();
                discordClient.getChannelByID(channel).sendMessage("**Commands & Config Reloaded**");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
