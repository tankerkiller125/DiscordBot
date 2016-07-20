package ml.rhodes.discordbot.commands.admin;

import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.util.Image;

import static ml.rhodes.discordbot.Core.config;
import static ml.rhodes.discordbot.Core.discordClient;

public class ChangeAvatar implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        Boolean command = event.getMessage().getContent().startsWith("-changeavatar");
        String[] args = event.getMessage().getContent().split(" ");

        if (command && event.getMessage().getAuthor().getDiscriminator().equals(config.getString("discord.owner"))) {
            try {
                discordClient.changeAvatar(Image.forUrl("png", args[1]));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
