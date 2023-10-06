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
        Issue issue=new Issue();
        issue.setLatitude(63.52);
        issue.setLongitude(13.419998);
        System.out.println(messageAdapter.createStationMessage(issue));
    }
    @Test
    void t(){
        double lat= 10.65, longu=52.65;
        String s="https://api.open-meteo.com/v1/forecast?latitude="+52.52+"&+longitude="+13.41+"&hourly=temperature_2m,relativehumidity_2m,windspeed_10m&current_weather=true&temperature_unit=celsius&timeformat=unixtime&forecast_days=1";
        System.out.println(s);
    }

}