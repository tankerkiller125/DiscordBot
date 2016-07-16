package ml.rhodes.discordbot.commands;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.common.base.Splitter;
import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static ml.rhodes.discordbot.Core.discordClient;

public class Youtube implements IListener<MessageReceivedEvent> {

    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static YouTube youtube;
    public void handle(MessageReceivedEvent event) {
        Boolean command = event.getMessage().getContent().startsWith("-youtube");
        String channel = event.getMessage().getChannel().getID();
        Pattern regex = Pattern.compile("https?:\\/\\/(?:[0-9A-Z-]+\\.)?(?:youtu\\.be\\/|youtube\\.com\\S*[^\\w\\-\\s])([\\w\\-]{11})(?=[^\\w\\-]|$)(?![?=&+%\\w]*(?:['\"][^<>]*>|<\\/a>))[?=&+%\\w]*", Pattern.CASE_INSENSITIVE);
        Matcher matcher = regex.matcher(event.getMessage().getContent());

        if (command) {
            try {
                discordClient.getChannelByID(channel).sendMessage("Youtube command not yet implemented");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            if (matcher.find()) {
                try {
                    Video v = getStats(event.getMessage().getContent().substring(matcher.start(), matcher.end()));
                    discordClient.getChannelByID(channel).sendMessage(
                            "**Duration**: " + v.getContentDetails().getDuration() + "\n" +
                                    "**Views**: " + v.getStatistics().getViewCount().toString() + "\n" +
                                    "**Likes**: " + v.getStatistics().getLikeCount().toString() + "\n" +
                                    "**Dislikes: " + v.getStatistics().getDislikeCount().toString() + "\n" +
                                    "**Comments: " + v.getStatistics().getCommentCount().toString()
                    );
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public String getVideoID(String videoUrl) throws MalformedURLException {
        URL url = new URL(videoUrl);
        String query = url.getQuery();
        Map<String, String> map = Splitter.on('&').trimResults().withKeyValueSeparator("=").split(query);
        return map.get("v");
    }

    public Video getStats(String videoURL) throws IOException{
        youtube = new YouTube.Builder(HTTP_TRANSPORT, JSON_FACTORY, new HttpRequestInitializer() {
            @Override
            public void initialize(HttpRequest httpRequest) throws IOException {
            }
        }).setApplicationName("TankBot").build();
        YouTube.Videos.List list = youtube.videos().list("statistics").setId(getVideoID(videoURL)).setKey("AIzaSyCBB1UY5PFQ8jtEpUIM7wjAu6kzBaI44-g");
        return list.execute().getItems().get(0);
    }
}
