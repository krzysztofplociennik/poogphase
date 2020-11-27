package com.plociennik.poogphase;

import com.plociennik.poogphase.model.ChatLog;
import com.plociennik.poogphase.model.ChatMessage;
import com.plociennik.poogphase.model.User;
import com.plociennik.poogphase.service.MessageManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

public class RandomTestingMain {

    private MessageManager messageManager;

    public static void main(String[] args) {
        MessageManager manager = new MessageManager();
        User mark = new User(), paula = new User(), cecil = new User();
        mark.setFirstName("Mark");
        paula.setFirstName("Paula");
        cecil.setFirstName("Cecil");

        ChatMessage chatMessage1 = new ChatMessage(1L, mark, paula, "Hey Paula!", LocalDateTime.of(
                LocalDate.of(2020, 11, 24), LocalTime.of(16, 52)));
        ChatMessage chatMessage2 = new ChatMessage(1L, paula, mark, "Oh, hey Mark! How are you?", LocalDateTime.of(
                LocalDate.of(2020, 11, 24), LocalTime.of(16, 54)));
        ChatMessage chatMessage3 = new ChatMessage(1L, mark, paula, "I'm fine, as always, how are you?", LocalDateTime.of(
                LocalDate.of(2020, 11, 24), LocalTime.of(16, 56)));
        ChatMessage chatMessage4 = new ChatMessage(1L, paula, mark, "Great, I'm great. Thanks!", LocalDateTime.of(
                LocalDate.of(2020, 11, 24), LocalTime.of(16, 57)));

        manager.sendMessage(mark, paula, chatMessage1);
        manager.sendMessage(paula, mark, chatMessage2);
        manager.sendMessage(mark, paula, chatMessage3);
        manager.sendMessage(paula, mark, chatMessage4);

        manager.showChatLog(paula, mark);
        manager.showChatLog(mark, cecil);
    }
}
