package ml.rhodes.discordbot.util;

public class WeatherUnderground {

    private String cityID;

    public boolean setCityID(String cityID) {
        this.cityID = cityID;
        return true;
    }

    public String getCityID(String search) {
        this.cityID = "";
        return this.cityID;
    }

    public String getWindChill() {
        return "";
    }

    public String getTemp() {
        return "";
    }

}
