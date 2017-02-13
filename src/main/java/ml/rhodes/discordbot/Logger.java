package ml.rhodes.discordbot;

import sx.blah.discord.api.events.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

class Logger implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        try {
            if (!event.getMessage().getChannel().isPrivate()) {
                System.out.println(event.getMessage().getGuild().getName() + " : #" + event.getMessage().getChannel().getName() + " : @" + event.getMessage().getAuthor().getName() + " : " + event.getMessage().getContent());
            } else {
                System.out.println(event.getMessage().getAuthor().getName() + " : " + event.getMessage().getContent());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
