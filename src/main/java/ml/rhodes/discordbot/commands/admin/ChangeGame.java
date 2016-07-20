package ml.rhodes.discordbot.commands.admin;

import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import java.util.Optional;

import static ml.rhodes.discordbot.Core.config;
import static ml.rhodes.discordbot.Core.discordClient;

public class ChangeGame implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        Boolean command = event.getMessage().getContent().startsWith("-changegame");
        String[] args = event.getMessage().getContent().split(" ");
        StringBuilder game = new StringBuilder();
        if (command && event.getMessage().getAuthor().getID().equals(config.getString("discord.owner"))) {
            try {
                if (args.length >= 1) {
                    for (int i = 1; i < args.length; i++) {
                        game.append(" ").append(args[i]);
                    }
                }
                discordClient.updatePresence(false, Optional.of(game.toString()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
