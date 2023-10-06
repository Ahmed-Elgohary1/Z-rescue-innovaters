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
  String ENDPOINT_URL ="https://api.open-meteo.com/v1/forecast?latitude="+issue.getLatitude()+"&longitude="+issue.getLongitude()+"&hourly=temperature_2m,relativehumidity_2m,windspeed_10m&current_weather=true&temperature_unit=celsius&timeformat=unixtime&forecast_days=1";
  JSONObject jsonObject = apiWeatherStatusGetter.getApiResponse(ENDPOINT_URL);



  Integer temp;
  Integer hum;
  Integer speed;


  temp= jsonObject.getJSONObject("current_weather").getInt("temperature");
  speed= jsonObject.getJSONObject("current_weather").getInt("windspeed");


  Integer timeStamp = jsonObject.getJSONObject("current_weather").getInt("time");
  JSONArray allHours = jsonObject.getJSONObject("hourly").getJSONArray("time");
  int timeIndex = findInArray(timeStamp,allHours);
  hum=jsonObject.getJSONObject("hourly").getJSONArray("relativehumidity_2m").getInt(timeIndex);

 report.setTemperature(temp);
 report.setHumidity(hum);
 report.setWindspeed(speed);

 report.setIssue(issue);

  calculate(report);
  ranking(report);
  return report;
 }

 void ranking(Report report){
  int score= report.getScore();
  String s;

  if(score>=85)
   s="Extreme";
  else if(score>=70)
   s="Very high";
  else if(score>=60)
   s="High";
  else if(score>=40)
   s="Moderate";
  else if(score>=20)
   s="Low";
  else
   s="Very low";

  report.setRank(s);


  System.out.println(s+ "score = "+score);
 }

 void calculate(Report report){

  int score=0;

  // Temperature
  int temp= report.getTemperature();
  if(temp>=40)
    score+=30;
  else if(temp>=35)
    score+=20;
  else if(temp>=30)
    score+=10;
  else if(temp>=25)
    score+=5;

  // humidity
  int hum=report.getHumidity();
  if(hum<=20)
   score+=20;
  else if(hum<=60)
   score+=10;

  // wind speed
  int speed=report.getWindspeed();

  if(speed>=14)
    score+=10;

  report.setScore(score);


 }

 public static int findInArray(Integer target, JSONArray array) throws JSONException {

  for (int i = 0; i < array.length(); i++)
   if (array.getNumber(i).equals(target))
    return i;

  return array.length()-1;
 }


}
