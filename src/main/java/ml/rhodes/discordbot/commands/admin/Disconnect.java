package ml.rhodes.discordbot.commands.admin;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.DiscordDisconnectedEvent;

import static ml.rhodes.discordbot.Core.apiKey;
import static ml.rhodes.discordbot.Core.getClient;
import static sx.blah.discord.handle.impl.events.DiscordDisconnectedEvent.Reason.LOGGED_OUT;

public class Disconnect implements IListener<DiscordDisconnectedEvent> {
    public void handle(DiscordDisconnectedEvent event) {
        System.out.println("Disconnected : " + event.getReason());
        if (!event.getReason().equals(LOGGED_OUT)) {
            try {
                getClient(apiKey);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
