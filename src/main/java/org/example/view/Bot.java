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
        return null;
    }

    @Override
    public String getBotToken() {
        return "1053198577:AAGiYiWZdrtdGcR0h9DRfKhYkM-U0v0AULo";
    }
//        if (update.hasMessage()) {
//            if (update.getMessage().hasText()) {
//                String textMessage = update.getMessage().getText();
//                Message message = update.getMessage();
//                System.out.println(message.getFrom().getFirstName() + " " + message.getFrom().getId() + " " + message.getChatId() + " " + textMessage);
//                try {
//                    if (textMessage.equals("тест")) {
//                        execute(sendInlineKeyBoardMessage(message.getChatId()));
//                    } else if (textMessage.matches(".*[Пп]олучается.*")) {
//                        execute(executeMessage(update, "пидр " + message.getFrom().getFirstName()));
//                    } else if (textMessage.matches("0.*") && message.getChatId() == -423999195) {
//                        execute(new SendMessage().setChatId(-1001214977124L).setText(textMessage.replaceAll("0", "")));
//                    }
//                } catch (TelegramApiException e) {
//                    e.printStackTrace();
//                }
//            }
//        } else if (update.hasCallbackQuery()) {
//
//            if (update.getCallbackQuery().getFrom().getId() == 948853209) return;
//            try {
//                if (update.getCallbackQuery().getData().equals("1")) {
//                    execute(new SendMessage().setText(new Weather().getWeather())
//                            .setChatId(update.getCallbackQuery().getMessage().getChatId()));
//                } else if (update.getCallbackQuery().getData().equals("BTS")) {
//                    execute(new SendMessage().setText("Ким Нам Джун, Ким Сокчин, Мин Юнги, Чон Хосок, Пак Чимин" +
//                            " Ким Тхэхён, Чон Джонгук, " + update.getCallbackQuery().getFrom().getFirstName())
//                            .setChatId(update.getCallbackQuery().getMessage().getChatId()));
//                } else {
//                    execute(new SendMessage().setText(update.getCallbackQuery().getFrom().getFirstName() + " пидр")
//                            .setChatId(update.getCallbackQuery().getMessage().getChatId()));
//                }
//            } catch (TelegramApiException e) {
//                e.printStackTrace();
//            }
//        }

}