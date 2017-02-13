package ml.rhodes.discordbot.commands.admin;

import ml.rhodes.discordbot.util.User;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.Image;

import static ml.rhodes.discordbot.Core.discordClient;

public class ChangeAvatar implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        Boolean command = event.getMessage().getContent().startsWith("-changeavatar");
        String[] args = event.getMessage().getContent().split(" ");

        if (command && User.isOwner(event.getMessage().getAuthor().getID())) {
            try {
                discordClient.changeAvatar(Image.forUrl("png", args[1]));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
