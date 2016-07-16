package ml.rhodes.discordbot.commands;

import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.DiscordDisconnectedEvent;

import java.util.Objects;

import static ml.rhodes.discordbot.Core.apiKey;
import static ml.rhodes.discordbot.Core.getClient;

public class Disconnect implements IListener<DiscordDisconnectedEvent> {
    public void handle(DiscordDisconnectedEvent event) {
        System.out.println("Disconnected : " + event.getReason());
        if (!Objects.equals("LOGGED_OUT", event.getReason())) {
            try {
                getClient(apiKey);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
