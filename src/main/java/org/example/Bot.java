package org.example;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import static org.example.Controller.*;
import static org.example.Keyboard.*;

public class Bot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                String textMessage = update.getMessage().getText();
                Message message = update.getMessage();
                try {
                    if (textMessage.equals("тест")) {
                        execute(sendInlineKeyBoardMessage(message.getChatId()));
                    } else if (textMessage.matches(".*[Пп]олучается.*")) {
                        execute(executeMessage(update, "пидр"));
                    } else if (textMessage.matches("0.*") && message.getChatId() == -423999195) {
                        execute(new SendMessage().setChatId(-1001214977124L).setText(textMessage.replaceAll("0", "")));
                    }
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } /*else if (update.hasCallbackQuery()) {

            if (update.getCallbackQuery().getFrom().getId() == 948853209) return;
            try {
                long startTime = System.currentTimeMillis();
                if (hashMapCount.get("Ограничение") != null && startTime < hashMapCount.get("Ограничение") + 1000 * 60) {
                    if (isGoMessage) {
                        execute(new SendMessage().setText("Хватит баловаться")
                                .setChatId(update.getCallbackQuery().getMessage().getChatId()));
                        isGoMessage = false;
                    }
                    return;
                } else {
                    isGoMessage = true;
                    hashMapCount.put("Ограничение", startTime);
                }

                if (update.getCallbackQuery().getData().equals("1")) {
                    execute(new SendMessage().setText(new Weather().getWeather())
                            .setChatId(update.getCallbackQuery().getMessage().getChatId()));
                } else if (update.getCallbackQuery().getData().equals("BTS")) {
                    execute(new SendMessage().setText("Ким Нам Джун, Ким Сокчин, Мин Юнги, Чон Хосок, Пак Чимин" +
                            " Ким Тхэхён, Чон Джонгук, " + update.getCallbackQuery().getFrom().getFirstName())
                            .setChatId(update.getCallbackQuery().getMessage().getChatId()));
                } else {
                    execute(new SendMessage().setText(update.getCallbackQuery().getFrom().getFirstName() + " пидр")
                            .setChatId(update.getCallbackQuery().getMessage().getChatId()));
                }
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }*/
    }


    @Override
    public String getBotUsername() {
        return null;
    }

    @Override
    public String getBotToken() {
        return "1053198577:AAGiYiWZdrtdGcR0h9DRfKhYkM-U0v0AULo";
    }


}