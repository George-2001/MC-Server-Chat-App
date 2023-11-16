package com.example.myapplication;

import android.graphics.Bitmap;

public class ServerModel {

    String address;
    String serverName;
    String version;
    String messageOfTheDay;
    String json;
    int playerCount = 0;
    int maxPlayers = 0;
    Bitmap image;
    int port;


    public ServerModel(String address, String name, int port) {
        this.address = address;
        serverName = name;
        this.port = port;
    }

    public String getaddress() {
        return address;
    }

    public void setaddress(String address) {
        this.address = address;
    }


    public String getserverName() {
        return serverName;
    }

    public void setserverName(String name) {
        serverName = name;
    }


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }


    public String getmessageOfTheDay() {
        return messageOfTheDay;
    }

    public void setmessageOfTheDay(String messageOfTheDay) {
        this.messageOfTheDay = messageOfTheDay;
    }


    public String getStoredJson() {
        return json;
    }

    public void setStoredJson(String json) {
        this.json = json;
    }


    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int playerCount) {
        this.playerCount = playerCount;
    }


    public int getMaxPlayers() { return maxPlayers; }

    public void setMaxPlayers(int maxPlayers) { this.maxPlayers = maxPlayers; }


    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }


    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
