package ml.rhodes.discordbot.commands.admin;

import ml.rhodes.discordbot.util.User;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import static ml.rhodes.discordbot.Core.discordClient;

public class ChangeName implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        Boolean command = event.getMessage().getContent().startsWith("-changename");

        if (command && User.isOwner(event.getMessage().getAuthor().getID())) {
            try {
                discordClient.changeUsername(event.getMessage().getContent().split(" ")[1]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
