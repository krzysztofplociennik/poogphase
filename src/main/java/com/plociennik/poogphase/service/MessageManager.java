package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.ChatLog;
import com.plociennik.poogphase.model.ChatMessage;
import com.plociennik.poogphase.model.User;

import java.util.ArrayList;

public class MessageManager {

    public void sendMessage(User author, User recipient, ChatMessage chatMessage) {
        author.getMessages().add(chatMessage);
        if (!author.getChatArchive().containsKey(recipient)) {
            author.getChatArchive().put(recipient,
                    new ChatLog(1L, author, recipient, new ArrayList<>()));
            recipient.getChatArchive().put(author,
                    new ChatLog(1L, recipient, author, new ArrayList<>()));
        }
        author.getChatArchive().get(recipient).getLog().add(chatMessage);
        recipient.getChatArchive().get(author).getLog().add(chatMessage);
    }

    public void showChatLog(User user1, User user2) {
        if (user1.getChatArchive().containsKey(user2)) {
            System.out.println("Chat log of " + user1.getFirstName() + " and " + user2 + ":\n");
            for (ChatMessage message : user1.getChatArchive().get(user2).getLog()) {
                System.out.println(message.getDateTime().toString() + " " + message.getAuthor().getFirstName() + ": " + message.getContent());
            }
        } else {
            System.out.println("There is no chat log yet");
        }
    }
}