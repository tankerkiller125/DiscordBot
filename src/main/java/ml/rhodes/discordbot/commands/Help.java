package ml.rhodes.discordbot.commands;

import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IPrivateChannel;

import static ml.rhodes.discordbot.Core.discordClient;
import static ml.rhodes.discordbot.Core.version;

public class Help implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        Boolean command = event.getMessage().getContent().startsWith("-help");
        String content =
                "**Owner: tankerkiller125**\n" +
                        "**Version: " + version + "**\n" +
                        "__**Commands:**__\n" +
                        "    **-help** : Gives you this menu\n" +
                        "    **-weather {city},{country code (optional)}** : Current weather for that city\n" +
                        "    **-youtube {id}** : Get youtube stats\n" +
                        "    **-repostats {owner}/{repository}** : Get GitHub repository stats";


        if (command) {
            try {
                IPrivateChannel channel = discordClient.getOrCreatePMChannel(discordClient.getUserByID(event.getMessage().getAuthor().getID()));
                channel.sendMessage(content);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
