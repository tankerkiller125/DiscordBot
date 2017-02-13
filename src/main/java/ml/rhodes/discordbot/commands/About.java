package ml.rhodes.discordbot.commands;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import static ml.rhodes.discordbot.Core.discordClient;
import static ml.rhodes.discordbot.Core.version;

public class About implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        Boolean command = event.getMessage().toString().startsWith("-about");
        String channel = event.getMessage().getChannel().getID();
        String userID = event.getMessage().getAuthor().getID();

        if (command) {
            try {
                discordClient.getChannelByID(channel).sendMessage("<@!" + userID + "> Check your PM for about information");
                discordClient.getUserByID(userID).getOrCreatePMChannel().sendMessage(
                        "__**About**__\n" +
                                "\n" +
                                "**Description:** Galaxy-MC Discord Bot\n" +
                                "**Version:** " + version + "\n" +
                                "**Created By:** tankerkiller125");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
