package ml.rhodes.discordbot.commands;

import org.bitpipeline.lib.owm.OwmClient;
import org.bitpipeline.lib.owm.WeatherData;
import org.bitpipeline.lib.owm.WeatherData.WeatherCondition;
import org.bitpipeline.lib.owm.WeatherStatusResponse;
import sx.blah.discord.api.IListener;
import sx.blah.discord.handle.impl.events.MessageReceivedEvent;

import java.net.URLEncoder;

import static ml.rhodes.discordbot.Core.discordClient;

public class Weather implements IListener<MessageReceivedEvent> {
    public void handle(MessageReceivedEvent event) {
        Boolean command = event.getMessage().getContent().startsWith("-weather");
        String channel = event.getMessage().getChannel().getID();
        String[] args = event.getMessage().getContent().split(" ");
        StringBuilder city = new StringBuilder();
        WeatherStatusResponse currentWeather;

        if (command) {
            try {
                if (args.length >= 1) {
                    for (int i = 1; i < args.length; i++) {
                        city.append(args[i]).append(" ");
                    }
                }
                OwmClient owm = new OwmClient();
                currentWeather = owm.currentWeatherAtCity(URLEncoder.encode(city.toString(), "UTF-8"));
                if (currentWeather.hasWeatherStatus()) {
                    WeatherData weatherData = currentWeather.getWeatherStatus().get(0);
                    WeatherCondition weatherCondition = weatherData.getWeatherConditions().get(0);
                    Double celcius = weatherData.getTemp() - 273.15;
                    Double fahrenheit = celcius * 9/5 + 32;
                    discordClient.getChannelByID(channel).sendMessage(
                            "**Temperature**: " + weatherData.getTemp() + "K, " + celcius.toString().substring(0,5) + "C, " + fahrenheit.toString().substring(0,5) + "F\n" +
                                    "**Humidity**: " + weatherData.getHumidity() + "%\n" +
                                    "**Description**: " + weatherCondition.getDescription() + "\n" +
                                    "**Pressure**: " + weatherData.getPressure()
                    );
                } else {
                    discordClient.getChannelByID(channel).sendMessage("Weather not available for that city");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
