package ml.rhodes.discordbot.util;

import static ml.rhodes.discordbot.Core.config;

public class User {

    /**
     * Checks if user is bot owner
     *
     * @param userID
     * @return boolean
     */
    public static boolean isOwner(String userID) {
        return config.getStringList("discord.owners").contains(userID);
    }

    /**
     * Checks if user is bot moderator
     *
     * @param userID
     * @return
     */
    public static boolean isModerator(String userID) {
        return config.getStringList("discord.moderators").contains(userID);
    }
}
