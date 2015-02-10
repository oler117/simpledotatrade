package com.testproject.domain;

/**
 * Created by oler on 10.02.2015.
 */
public class Trader {

    private String steamName;
    private String steamURL;
    private String avatarURL;

    public Trader() {
    }

    public Trader(String steamName, String steamURL, String avatarURL) {
        this.steamName = steamName;
        this.steamURL = steamURL;
        this.avatarURL = avatarURL;
    }

    public String getSteamName() {
        return steamName;
    }

    public void setSteamName(String steamName) {
        this.steamName = steamName;
    }

    public String getSteamURL() {
        return steamURL;
    }

    public void setSteamURL(String steamURL) {
        this.steamURL = steamURL;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Trader)) return false;

        Trader trader = (Trader) o;

        if (!avatarURL.equals(trader.avatarURL)) return false;
        if (!steamName.equals(trader.steamName)) return false;
        if (!steamURL.equals(trader.steamURL)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = steamName.hashCode();
        result = 31 * result + steamURL.hashCode();
        result = 31 * result + avatarURL.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Trader{" +
                "steamName='" + steamName + '\'' +
                ", steamURL='" + steamURL + '\'' +
                ", avatarURL='" + avatarURL + '\'' +
                '}';
    }
}
