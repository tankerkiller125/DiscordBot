package ml.rhodes.discordbot.commands.github;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import static ml.rhodes.discordbot.Core.config;
import static ml.rhodes.discordbot.Core.discordClient;

public class RepoStats implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        Boolean command = event.getMessage().getContent().startsWith("-repostats");
        String channel = event.getMessage().getChannel().getID();

        if (command) {
            try {
                String[] args = event.getMessage().getContent().split(" ");
                String[] repo = args[1].split("/");

                GitHubClient client = new GitHubClient();
                client.setOAuth2Token(config.getString("apiKeys.github"));
                RepositoryService service = new RepositoryService(client);
                Repository repository = service.getRepository(repo[0], repo[1]);
                discordClient.getChannelByID(channel).sendMessage(
                        "**Name**: " + repository.getName() + "\n" +
                                "**Description**: " + repository.getDescription() + "\n" +
                                "**Forks**: " + repository.getForks() + "\n" +
                                "**Watchers**: " + repository.getWatchers() + "\n" +
                                "**Language**: " + repository.getLanguage() + "\n" +
                                "**Open Issues**: " + repository.getOpenIssues() + "\n" +
                                "**URL**: " + repository.getHtmlUrl());
            } catch (Exception e) {
                try {
                    discordClient.getChannelByID(channel).sendMessage("Error: " + e.getMessage());
                } catch (Exception error) {
                    System.out.println("[RepoStats] " + error.getMessage());
                }
                System.out.println("[RepoStats] " + e.getMessage());
            }
        }
    }
}
