package org.example;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bot extends TelegramLongPollingBot {
    Map<String, Long> hashMapCount = new HashMap<>();
    Map<String, Integer> hashMapId = new HashMap<>();
    boolean isGoMessage = true;

    @Override
    public void onUpdateReceived(Update update) {


        if (update.hasMessage()) {
            String name = update.getMessage().getFrom().getFirstName();
            hashMapCount.merge(name, 1L, (vOld, vNew) -> vOld += vNew);
            hashMapId.put(name, update.getMessage().getFrom().getId());

            if (update.getMessage().hasText()) {
                try {
                    if (update.getMessage().getText().equals("тест")) {
                        execute(sendInlineKeyBoardMessage(update.getMessage().getChatId()));
                    } else if (update.getMessage().getText().matches(".*[Пп]олучается.*") && update.getMessage().getFrom().getId() != 473957341) {
                        execute(new SendMessage().setChatId(update.getMessage().getChatId())
                                .setText(update.getMessage().getFrom().getFirstName() + " пидр"));
                    } else if (update.getMessage().getText().equals("статистика") && update.getMessage().getFrom().getId() == 473957341) {
                        execute(new SendMessage().setChatId(update.getMessage().getChatId())
                                .setText(hashMapCount.toString() + "\n "
                                        + hashMapId.toString()));
                    } else if (update.getMessage().getText().matches("0.*") && update.getMessage().getChatId() == -423999195) {
                        execute(new SendMessage().setChatId(-1001214977124L).setText(update.getMessage().getText().replaceAll("0", "")));
                    }
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } else if (update.hasCallbackQuery()) {

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

    public SendMessage sendInlineKeyBoardMessage(long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        InlineKeyboardButton inlineKeyboardButton1 = new InlineKeyboardButton();
        InlineKeyboardButton inlineKeyboardButton2 = new InlineKeyboardButton();
        inlineKeyboardButton1.setText("Погода").setCallbackData("1");
        inlineKeyboardButton2.setCallbackData("Тестировщики хуевы").setText("Тест");
        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow1.add(inlineKeyboardButton1);
        keyboardButtonsRow1.add(new InlineKeyboardButton().setText("Состав BTS").setCallbackData("BTS"));
        keyboardButtonsRow2.add(inlineKeyboardButton2);
        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);
        inlineKeyboardMarkup.setKeyboard(rowList);
        return new SendMessage().setChatId(chatId).setText("Новый формат бота пыльчаны").setReplyMarkup(inlineKeyboardMarkup);
    }
}