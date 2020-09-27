package org.example.model;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.sun.deploy.net.HttpResponse;
import org.apache.http.HttpRequest;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

@Component
public class Weather {
    private Document document;

    public Weather() {
        connect();
    }

    private void connect() {
        try {
            document = Jsoup.connect("https://www.google.com/search?q=%D0%BF%D0%BE%D0%B3%D0%BE%D0%B4%D0%B0+%D0%B8%D0%B6%D0%B5%D0%B2%D1%81%D0%BA&oq=%D0%BF%D0%BE%D0%B3%D0%BE&aqs").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getWeather() {
        Element element = document.getElementById("wob_dp");
        StringBuilder s = new StringBuilder();
        String[] w = element.text().split("[°]");
        for (int i = 0; i < w.length; i += 2)
            s.append(String.format("%s°\\%s°\n", w[i].trim().replaceAll("\\d{2}$", ""), w[i + 1].trim().replaceAll("\\d{2}$", "")));
        return s.toString();
    }

    public String getWeatherApi() {
        String weatherString = "Сегодня братцы \n";
        try {
            String url = "http://api.openweathermap.org/data/2.5/forecast?appid=95c82bba8cafb698e2d041ca55b371d0&q=izhevsk&units=metric&lang=ru";

            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();

            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            System.out.println(response.toString());

            JSONObject json = new JSONObject(response.toString());
            JSONArray list = (JSONArray) json.get("list");
            JSONObject jsonDay = (JSONObject) list.get(0);
            String day = ((String) jsonDay.get("dt_txt")).replaceAll(" .*", "");

            for (int i = 1; true; i++) {

                JSONObject jsonDayFor = (JSONObject) list.get(i);
                String dayFor = ((String) jsonDayFor.get("dt_txt")).replaceAll(" .*", "");
                JSONArray weather = (JSONArray) jsonDay.get("weather");
                JSONObject weatherObject = (JSONObject) weather.get(0);
                JSONObject main = (JSONObject) jsonDayFor.get("main");

                if (!dayFor.equals(day)) {
                    break;
                }
                weatherString += "- "+ (((String) jsonDayFor.get("dt_txt")).replaceAll(".* ", "Сегодня в ") + " будет " + weatherObject.get("description") + ", а по температуре " + main.get("temp") + "°") + "\n";
            }
        }catch (IOException e){
            weatherString = "- траблы с сервисов апи";
        }

        return weatherString;
    }

}