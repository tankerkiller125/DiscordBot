package ml.rhodes.discordbot.commands.admin;

import ml.rhodes.discordbot.util.User;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import static ml.rhodes.discordbot.Core.discordClient;

public class Logout implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        Boolean command = event.getMessage().getContent().startsWith("-logout");
        String channel = event.getMessage().getChannel().getID();

        if (command && User.isOwner(event.getMessage().getAuthor().getID())) {
            try {
                discordClient.getChannelByID(channel).sendMessage("Exiting");
                discordClient.logout();
                System.exit(0);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
