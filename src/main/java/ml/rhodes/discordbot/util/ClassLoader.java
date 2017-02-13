package ml.rhodes.discordbot.util;

import ml.rhodes.discordbot.commands.*;
import ml.rhodes.discordbot.commands.admin.ChangeAvatar;
import ml.rhodes.discordbot.commands.admin.ChangeGame;
import ml.rhodes.discordbot.commands.admin.ChangeName;
import ml.rhodes.discordbot.commands.admin.Logout;
import sx.blah.discord.api.events.IListener;

import static ml.rhodes.discordbot.Core.discordClient;

public class ClassLoader {

    private static IListener[] classes = {
            // Admin Commands
            new Logout(),
            new ChangeAvatar(),
            new ChangeGame(),
            new ChangeName(),

            // Standard Commands
            new Weather(),
            new Youtube(),
            new Help(),
            new About(),

            //MC Commands
            new Servers()
    };

    public void unregister() {
        for (IListener command : classes) {
            discordClient.getDispatcher().unregisterListener(command);
        }
    }

    public void register() {
        for (IListener command : classes) {
            discordClient.getDispatcher().registerListener(command);
        }
    }
}
