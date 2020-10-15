package org.example.controller;


import org.example.model.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

@Component
@Scope("prototype")
public class Controller {

    private boolean isMessageForBot = false;
    private Update update;
    private SendMessage sendMessage;

    @Autowired
    private Weather weather;

    public boolean isMessageForBot() {
        return isMessageForBot;
    }

    public void setMessage(Update update) {
        this.update = update;
        parseMessage();
    }

    public SendMessage getMessage() {
        return sendMessage;
    }

    private void setMessageWeather() {
        sendMessage = new SendMessage().setChatId(update.getMessage().getChatId()).setText(weather.getWeatherApi());
    }

    private void parseMessage() {
        if (update.getMessage().getText().equals("Погода")) {
            isMessageForBot = true;
            setMessageWeather();
        }
    }


}
