package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

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
}