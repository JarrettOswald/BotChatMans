package org.example.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class Weather {

    public String getWeatherApi() {
        String weatherString;
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
            StringBuilder result1 = new StringBuilder();
            JSONObject json1 = new JSONObject(response.toString());
            JSONArray list1 = (JSONArray) json1.get("list");
            Map<String, String> map = new LinkedHashMap<>();
            for (Object dayJson : list1) {
                String dataFull = (String) ((JSONObject) dayJson).get("dt_txt");
                String weatherDescription = (String) ((JSONObject) ((JSONArray) ((JSONObject) dayJson).get("weather")).get(0)).get("description");

                weatherDescription = weatherDescription.replaceAll("ясно", "☀");
                weatherDescription = weatherDescription.replaceAll("переменная облачность", "\uD83C\uDF24");
                weatherDescription = weatherDescription.replaceAll("небольшая облачность", "\uD83C\uDF24");
                weatherDescription = weatherDescription.replaceAll("облачно с прояснениями", "\uD83C\uDF25");
                weatherDescription = weatherDescription.replaceAll("пасмурно", "☁");
                weatherDescription = weatherDescription.replaceAll("небольшой дождь", "\uD83C\uDF26");
                weatherDescription = weatherDescription.replaceAll("дождь", "\uD83C\uDF27");

                double temp = (double) ((JSONObject) ((JSONObject) dayJson).get("main")).get("temp");
                String[] dataTime = dataFull.split(" ");
                String timeHour = dataTime[1].replaceAll(":.*", "");
                String[] s = dataTime[0].split("-");
                LocalDate date = LocalDate.of(Integer.parseInt(s[0]), Integer.parseInt(s[1]), Integer.parseInt(s[2]));

                String formatValue = "%s %s:00 t°=%-5s";
                String formatKey = "%s %d-%d-%d";
                String key = String.format(formatKey, date.getDayOfWeek(), Integer.parseInt(s[2]), Integer.parseInt(s[1]), Integer.parseInt(s[0]));
                String value = String.format(formatValue, weatherDescription, timeHour, temp);
                map.merge(key, value, (oldValue, newValue) -> oldValue + "\n" + newValue);
            }
            for (Map.Entry s : map.entrySet()) {
                result1.append(s.getKey()).append("\n").append(s.getValue()).append("\n\n");
            }
            weatherString = result1.toString().replaceAll("\n", "\n");
            System.out.println(weatherString);
        } catch (IOException e) {
            weatherString = "- траблы с сервисов апи";
        }

        return weatherString;
    }

}