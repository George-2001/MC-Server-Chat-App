package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import org.json.JSONException;
import org.json.JSONObject;

public class JSONDecoder {
    String version;
    int playerCount;
    int playerMax;
    String description;
    String image;
    Context context;
    ObjectMapper objectMapper = new ObjectMapper();
    ObjectNode node;
    JSONObject reader;
    JSONObject serverInfo;


    public int getPlayerCount(String json) throws JsonProcessingException, JSONException {
        reader = new JSONObject(json);
        serverInfo  = reader.getJSONObject("players");
        playerCount = serverInfo.getInt("online");

        return playerCount;

    }

    public int getPlayerMax(String json) throws JsonProcessingException, JSONException {
        reader = new JSONObject(json);
        serverInfo  = reader.getJSONObject("players");
        playerMax = serverInfo.getInt("max");

        return playerMax;
    }

    public String getVersion(String json) throws JSONException {
        reader = new JSONObject(json);
        serverInfo  = reader.getJSONObject("version");
        version = serverInfo.getString("name");
        return version;
    }



    public Bitmap getImage(String json) throws JSONException {
        reader = new JSONObject(json);
        image = reader.getString("favicon");
        image = image.substring(22);
        // decode base64 string
        byte[] bytes= Base64.decode(image,Base64.DEFAULT);
        // Initialize bitmap
        Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
        // set bitmap on imageView
        //imageView.setImageBitmap(bitmap);

        return bitmap;
    }

    public JSONDecoder() {
    }


}
