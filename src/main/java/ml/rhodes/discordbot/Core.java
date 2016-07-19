package ml.rhodes.discordbot;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import sx.blah.discord.api.ClientBuilder;
import sx.blah.discord.api.IDiscordClient;
import sx.blah.discord.util.DiscordException;

public class Core {

    public static IDiscordClient discordClient;
    public static String version = "0.0.2";
    public static String apiKey = null;
    public static Config config = null;

    public static void main(String[] args) throws Exception {
        if (System.getenv("ENV") != null) {
            config = ConfigFactory.load();
        } else {
            config = ConfigFactory.load(System.getenv("ENV"));
        }
        if (args.length < 1)
            System.out.print("You need to specify a token as an argument");
        apiKey = args[0];
        discordClient = getClient(apiKey);
        discordClient.getDispatcher().registerListener(new Ready());
    }

    public static IDiscordClient getClient(String token) throws DiscordException {
        return new ClientBuilder().withToken(token).login();
    }
}
