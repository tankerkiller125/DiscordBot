package ml.rhodes.discordbot.util;

import ml.rhodes.discordbot.commands.Help;
import ml.rhodes.discordbot.commands.Weather;
import ml.rhodes.discordbot.commands.Youtube;
import ml.rhodes.discordbot.commands.admin.ChangeAvatar;
import ml.rhodes.discordbot.commands.admin.ChangeGame;
import ml.rhodes.discordbot.commands.admin.ChangeName;
import ml.rhodes.discordbot.commands.admin.Logout;
import ml.rhodes.discordbot.commands.github.CommitLinker;
import ml.rhodes.discordbot.commands.github.IssueLinker;
import ml.rhodes.discordbot.commands.github.RepoLinker;
import ml.rhodes.discordbot.commands.github.RepoStats;
import sx.blah.discord.api.IListener;

import static ml.rhodes.discordbot.Core.discordClient;

public class ClassLoader {

    private static IListener[] classes = {
            // Admin Commands
            new Logout(),
            new ChangeAvatar(),
            new ChangeGame(),
            new ChangeName(),

            // Standard Commands
            new Help(),
            new Weather(),
            new Youtube(),

            // GitHub Commands
            new RepoStats(),
            new RepoLinker(),
            new IssueLinker(),
            new CommitLinker()
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
