package ml.rhodes.discordbot.commands;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import static ml.rhodes.discordbot.Core.discordClient;

public class Help implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        Boolean command = event.getMessage().toString().startsWith("-help");
        String channel = event.getMessage().getChannel().getID();
        String userID = event.getMessage().getAuthor().getID();

        if (command) {
            try {
                discordClient.getChannelByID(channel).sendMessage("<@!" + userID + "> Check your PM for help information");
                discordClient.getUserByID(userID).getOrCreatePMChannel().sendMessage(
                        "__**Help**__:\n" +
                                "**Commands**\n" +
                                "  -help: _Display this menu_\n" +
                                "  -about: _Display information about bot_"
                );
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
