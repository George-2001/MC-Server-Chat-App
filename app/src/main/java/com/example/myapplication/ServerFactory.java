package com.example.myapplication;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class ServerFactory {
    ArrayList<ServerModel> serverModelArrayList;
    ServerConnector serverConnector;
    JSONDecoder jsonDecoder;

    public ServerFactory() {

        serverModelArrayList = new ArrayList<ServerModel>();
        serverConnector = new ServerConnector();
        jsonDecoder = new JSONDecoder();

    }

    public ArrayList<ServerModel> getServerModelArrayList() {
        return serverModelArrayList;
    }

    public void setServerModelArrayList(ArrayList<ServerModel> serverModelArrayList) {
        this.serverModelArrayList = serverModelArrayList;
    }

    public ServerModel formConnection(ServerModel server) throws IOException, JSONException {
        String address;
        int port;
        String json;
            address = server.getaddress();
            port = server.getPort();
            json = serverConnector.getServerConnectionJson(address, port);
            System.out.println(json);
            server.setStoredJson(json);
            server.setPlayerCount(jsonDecoder.getPlayerCount(json));
            server.setMaxPlayers(jsonDecoder.getPlayerMax(json));
            server.setImage(jsonDecoder.getImage(json));
            server.setVersion(jsonDecoder.getVersion(json));

        return server;
    }

    public ArrayList<ServerModel> setUpserverModelArrayList() throws IOException, JSONException {

        serverModelArrayList.add(new ServerModel("play.shotbow.net", "Shotbow", 25565));
        serverModelArrayList.add(new ServerModel("org.archonhq.net", "ArchonHQ", 25565));
        serverModelArrayList.add(new ServerModel("mc.pokewild.com", "PokeWild", 25565));
        serverModelArrayList.add(new ServerModel("play.tulipsurvival.com", "Tulip Survival", 25565));
        serverModelArrayList.add(new ServerModel("play.mineclub.com", "Mineclub", 25565));
        serverModelArrayList.add(new ServerModel("hub.mc-complex.com", "MC-Complex", 25565));
        serverModelArrayList.add(new ServerModel("mcs.mchub.com", "MC Hub", 25565));
        serverModelArrayList.add(new ServerModel("org.earthmc.net", "Earth MC", 25565));
        serverModelArrayList.add(new ServerModel("ms.minerival.org", "Mine Rival", 25565));
        serverModelArrayList.add(new ServerModel("org.melonsmp.net", "MelonSMP", 25565));
        serverModelArrayList.add(new ServerModel("mc.applecraft.org", "Apple Craft", 25565));
        serverModelArrayList.add(new ServerModel("one.lemoncloud.net", "Lemon Cloud", 25565));
        serverModelArrayList.add(new ServerModel("play.anubismc.com", "Anubis MC", 25565));
        serverModelArrayList.add(new ServerModel("play.dungeonrealms.net", "Dungeon Realms", 25565));
        serverModelArrayList.add(new ServerModel("play.wynncraft.com", "Wynn Craft", 25565));

        String json = "{\"version\":{\"name\":\"1.12.2 and above required\",\"protocol\":340},\"players\":{\"max\":1,\"online\":0,\"sample\":[]},\"description\":{\"text\":\"§r       §6§m\\u003e\\u003e\\u003e§f§m----§r §6Shotbow §7| §c1.12.2 - 1.18.2§r §f§m-----§6§m\\u003e§r\\n  §c§lANNI§e§lHI§9§lLATI§a§lON §b§lUPDATE - §a§lNEW CLASS: TANK!\"},\"favicon\":\"data:image/png;base64,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\",\"modinfo\":{\"type\":\"FML\",\"modList\":[]}}\n";
        //String json = serverConnector.getServerConnectionJson("play.shotbow.net", 25565);
        //json = "\"" + json + "\"";
        //json = String.valueOf(json.toCharArray(), 0, json.length());
        int pc = jsonDecoder.getPlayerCount(json);
        serverModelArrayList.get(0).setPlayerCount(pc);

        for (int i=0; i<serverModelArrayList.size(); i++) {
            //String json = serverConnector.getServerConnectionJson("play.shotbow.net", 25565);
            serverModelArrayList.get(i).setPlayerCount(jsonDecoder.getPlayerCount(json));
        }
        return serverModelArrayList;
    }

}
