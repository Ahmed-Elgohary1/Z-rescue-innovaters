package com.example.zRescue.service;

import com.example.zRescue.model.Issue;
import com.example.zRescue.model.Report;
import com.example.zRescue.service.ApiWeatherStatusGetter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MessageAdapter {

 private static MessageAdapter instance;

 private MessageAdapter() { }

 public static MessageAdapter getInstance() {
  if (instance == null) {
   synchronized (MessageAdapter.class) {
    if (instance == null) {
     instance = new MessageAdapter();
    }
   }
  }
  return instance;
 }


 public Report createStationMessage(Issue issue){

  Report report=new Report();

  ApiWeatherStatusGetter apiWeatherStatusGetter = ApiWeatherStatusGetter.getInstance();

  JSONObject jsonObject = apiWeatherStatusGetter.getApiResponse();

  System.out.println(jsonObject);


  Integer temp;
  Integer hum;
  Integer speed;


  temp= jsonObject.getJSONObject("current_weather").getInt("temperature");
  speed= jsonObject.getJSONObject("current_weather").getInt("windspeed");


  Integer timeStamp = jsonObject.getJSONObject("current_weather").getInt("time");
  JSONArray allHours = jsonObject.getJSONObject("hourly").getJSONArray("time");
  int timeIndex = findInArray(timeStamp,allHours);
 // hum=jsonObject.getJSONObject("hourly").getJSONArray("relativehumidity_2m").getInt(timeIndex);

 report.setTemperature(temp);
 //report.setHumidity(hum);
 report.setWindspeed(speed);
  System.out.println(report);

  return report;
 }


 public static int findInArray(Integer target, JSONArray array) throws JSONException {

  for (int i = 0; i < array.length(); i++)
   if (array.getNumber(i).equals(target))
    return i;

  return -1;
 }


}
