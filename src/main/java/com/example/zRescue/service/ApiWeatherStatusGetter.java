package com.example.zRescue.service;

import lombok.Data;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.logging.Logger;
@Data
public class ApiWeatherStatusGetter {



    private static ApiWeatherStatusGetter instance;

    private ApiWeatherStatusGetter() { }

    public static ApiWeatherStatusGetter getInstance() {
        if (instance == null) {
            synchronized (ApiWeatherStatusGetter.class) {
                if (instance == null) {
                    instance = new ApiWeatherStatusGetter();
                }
            }
        }
        return instance;
    }



    private static final Logger log = Logger.getLogger(ApiWeatherStatusGetter.class.getName());
   // private static  String ENDPOINT_URL ="https://api.open-meteo.com/v1/forecast?latitude="+52.52+"&+longitude="+13.41+"&hourly=temperature_2m,relativehumidity_2m,windspeed_10m&current_weather=true&temperature_unit=celsius&timeformat=unixtime&forecast_days=1";


    CloseableHttpClient httpClient = HttpClients.createDefault();

    public JSONObject getApiResponse(String ENDPOINT_URL) {
        String responseString;
        try {
            URL url = new URL(ENDPOINT_URL);
            // create HttpURLConnection object
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            // set request method
            con.setRequestMethod("GET");

            // get response code
            int responseCode = con.getResponseCode();
            log.info("Response code: " + responseCode);

            // read response content
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            responseString = response.toString();
            // print response content
            log.info("Response String"+responseString);
        } catch (MalformedURLException | ProtocolException e1) {
            throw new RuntimeException(e1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (Exception e2){
            throw new RuntimeException(e2);
        }

        return new JSONObject(responseString);
    }
}
