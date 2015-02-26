package com.example.anthonygrisaffi.roomez;


import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Messages")

public class Message extends ParseObject {

    public String getUserId() {
        return getString("userId");
    }

    public String getBody() {
        return getString("body");

    }

    public void setUserId(String userId) {
        put("userId", userId);
    }

    public void setBody(String body) {
        put("body", body);
    }
}