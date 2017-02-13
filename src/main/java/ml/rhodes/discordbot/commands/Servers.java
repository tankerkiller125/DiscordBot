package ml.rhodes.discordbot.commands;

import ml.rhodes.discordbot.util.minecraft.Query;
import ml.rhodes.discordbot.util.minecraft.QueryResponse;
import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import static ml.rhodes.discordbot.Core.discordClient;

public class Servers implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        Boolean command = event.getMessage().toString().startsWith("-server");
        String channel = event.getMessage().getChannel().getID();
        String userID = event.getMessage().getAuthor().getID();

        String[] args = event.getMessage().toString().split(" ");

        if (command) {
            try {
                if (args[1].isEmpty()) {
                    discordClient.getChannelByID(channel).sendMessage("<@!" + userID + "> Please specify server");
                } else {
                    String[] server = args[1].split(":");
                    Query query = new Query(server[0], Integer.parseInt(server[1]));
                    QueryResponse queryResponse = query.fullStat();
                    String hostname = queryResponse.getHostname();
                    String motd = queryResponse.getMOTD();
                    String players = String.valueOf(queryResponse.getOnlinePlayers());
                    String maxPlayers = String.valueOf(queryResponse.getMaxPlayers());
                    discordClient.getChannelByID(channel).sendMessage(
                            "**MOTD:** " + motd + "\n" +
                                    "**Players:** " + players + "/" + maxPlayers);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
