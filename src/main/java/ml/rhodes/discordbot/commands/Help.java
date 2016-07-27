package ml.rhodes.discordbot.commands;

import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;
import sx.blah.discord.handle.obj.IPrivateChannel;

import static ml.rhodes.discordbot.Core.*;

public class Help implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        Boolean command = event.getMessage().getContent().startsWith("-help");
        String content =
                "**Owner: " + discordClient.getUserByID(config.getString("discord.owner")).getName() + "**\n" +
                        "**Version: " + version + "**\n" +
                        "__**Commands:**__\n" +
                        "    **-help** : Gives you this menu\n" +
                        "    **-weather {city},{country code (optional)}** : Current weather for that city\n" +
                        "    **-youtube {id}** : Get youtube stats\n" +
                        "    **-repostats {owner}/{repository}** : Get GitHub repository stats\n" +
                        "\n" +
                        "__**Helpers:**__\n" +
                        "    **{user}/{repo}** : Basic info and link to Github Repo\n" +
                        "    **{user}/{repo}#{issue}** : Link to GitHub Issue/Pull Request\n" +
                        "    **{user}/{repo}@{commit}** : Link to GitHub Commit\n" +
                        "\n" +
                        "__**Admin Only:**__\n" +
                        "    **-changegame {text for game}** : Change the game the bots playing\n" +
                        "    **-changeavatar {url to a PNG}** : Change avatar to the PNG at the URL provided\n" +
                        "    **-changename {newUsername}** : Change the bots username\n" +
                        "    **-logout** : Does exactly what it sounds like would do\n" +
                        "\n" +
                        "For additional help join: https://discord.gg/0qwPf0WZWbygCeXG";


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
