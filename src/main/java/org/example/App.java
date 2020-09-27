package org.example;

import javafx.application.Application;
import org.example.context.Context;
import org.example.view.Bot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi telegram = new TelegramBotsApi();
        Bot bot = new Bot();
        try {
            telegram.registerBot(bot);
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}

