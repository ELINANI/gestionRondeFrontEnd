package com.example.gestionrondefrontend;

public class wifi {
    String ssid;
    String level;
    String timestamp;

    public wifi(String ssid, String level, String timestamp) {
        this.ssid = ssid;
        this.level = level;
        this.timestamp = timestamp;
    }

    public String getSsid() {
        return ssid;
    }

    public void setSsid(String ssid) {
        this.ssid = ssid;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
