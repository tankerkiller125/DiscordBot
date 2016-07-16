package ml.rhodes.discordbot.commands;

import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import static ml.rhodes.discordbot.Core.discordClient;

public class ChangeName implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        Boolean command = event.getMessage().getContent().startsWith("-changename");

        if (command) {
            try {
                discordClient.changeUsername(event.getMessage().getContent().split(" ")[1]);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
