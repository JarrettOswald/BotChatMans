package org.example;

import org.checkerframework.checker.units.qual.A;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;

public class Keyboard {
    private ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
    ArrayList<KeyboardRow> keyboardRows = new ArrayList<>();
    KeyboardRow keyboardRowFirst = new KeyboardRow();
    KeyboardRow keyboardRowSecond = new KeyboardRow();


    public ReplyKeyboardMarkup getReplyKeyboardMarkup() {
        keyboardRows.clear();
        keyboardRowFirst.add("АУФ");
        keyboardRowFirst.add("АУЕ");
        keyboardRowSecond.add("Жизнь ворам");
        keyboardRows.add(keyboardRowFirst);
        keyboardRows.add(keyboardRowSecond);
        replyKeyboardMarkup.setKeyboard(keyboardRows);
        return replyKeyboardMarkup;
    }

    public Keyboard(){
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

    }
}
