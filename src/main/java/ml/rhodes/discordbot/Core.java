package ml.rhodes.discordbot;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

public class Core {

    public static IDiscordClient discordClient;
    public static String version = "1.1.2";
    public static String apiKey = null;
    public static Config config = null;

    public static void main(String[] args) throws Exception {
        final String environment = System.getenv("ENV");
        if (environment == null) {
            System.out.println("Environment: Default");
            config = ConfigFactory.load();
        } else {
            System.out.println("Environment: " + environment);
            config = ConfigFactory.load(environment);
        }
        if (args.length >= 1) {
            apiKey = args[0];
        } else {
            if (!config.getString("discord.token").isEmpty()) {
                apiKey = config.getString("discord.token");
            }
        }
        discordClient = getClient(apiKey);
        discordClient.getDispatcher().registerListener(new Ready());
    }

    public static IDiscordClient getClient(String token) throws DiscordException {
        return new ClientBuilder().withToken(token).login();
    }
}
