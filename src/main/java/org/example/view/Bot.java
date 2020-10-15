package org.example.view;


//import org.example.Controller;

import org.example.context.Context;
import org.example.controller.Controller;
import org.example.model.Weather;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.example.Keyboard.*;

public class Bot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                Controller controller = Context.getInstance().getBean("controller", Controller.class);
                controller.setMessage(update);
                try {
                    if (controller.isMessageForBot())
                        execute(controller.getMessage());
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public String getBotUsername() {
        return "BotUsername";
    }

    @Override
    public String getBotToken() {
        return "1053198577:AAGiYiWZdrtdGcR0h9DRfKhYkM-U0v0AULo";
    }

}