package ml.rhodes.discordbot.commands.admin;

import ml.rhodes.discordbot.util.User;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.Status;

import static ml.rhodes.discordbot.Core.discordClient;

public class ChangeGame implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        Boolean command = event.getMessage().getContent().startsWith("-changegame");
        String[] args = event.getMessage().getContent().split(" ");
        StringBuilder game = new StringBuilder();
        if (command && User.isOwner(event.getMessage().getAuthor().getID())) {
            try {
                if (args.length >= 1) {
                    for (int i = 1; i < args.length; i++) {
                        game.append(" ").append(args[i]);
                    }
                }
                discordClient.changeStatus(Status.game(game.toString()));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
