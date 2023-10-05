package com.example.zRescue.service;

import com.example.zRescue.model.Issue;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ApiWeatherStatusGetterTest {

    @Test
    void call(){
        MessageAdapter messageAdapter=MessageAdapter.getInstance();
        System.out.println(messageAdapter.createStationMessage(new Issue()));
    }

}