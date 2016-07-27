package ml.rhodes.discordbot.commands.github;

import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.RepositoryService;
import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ml.rhodes.discordbot.Core.config;
import static ml.rhodes.discordbot.Core.discordClient;

public class RepoLinker implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        String channel = event.getMessage().getChannel().getID();

        String re1 = "((?:[a-z][a-z]*[0-9]*[a-z0-9\\w-]*))";    // Alphanumeric with Dashes and Underlines
        String re2 = "((?:\\/[\\w\\.\\-]+)+)";    // Path

        Pattern pattern = Pattern.compile(re1 + re2, Pattern.CASE_INSENSITIVE | Pattern.DOTALL | Pattern.MULTILINE);
        Matcher matcher = pattern.matcher(event.getMessage().getContent());
        if (matcher.find()) {
            matcher.reset();
            while (matcher.find()) {
                String user = matcher.group(1);
                String repo = matcher.group(2).replace("/", "");
                try {
                    GitHubClient client = new GitHubClient();
                    client.setOAuth2Token(config.getString("apiKeys.github"));
                    RepositoryService repositoryService = new RepositoryService(client);
                    Repository repository = repositoryService.getRepository(user, repo);
                    discordClient.getChannelByID(channel).sendMessage(
                            "**Name**: " + repository.getName() + "\n" +
                                    "**Description**: " + repository.getDescription() + "\n" +
                                    "**Language**: " + repository.getLanguage() + "\n" +
                                    "**URL**: " + repository.getHtmlUrl());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
