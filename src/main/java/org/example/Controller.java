package org.example;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Controller {

    public static SendMessage executeMessage(Update update, String message) {
        return new SendMessage().setChatId(update.getMessage().getChatId()).setText(message);
    }

    public static SendMessage executeMessageQuery(Update update, String message) {
        return new SendMessage().setChatId(update.getCallbackQuery().getMessage().getChatId()).setText(message);
    }


}
