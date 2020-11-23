package com.plociennik.poogphase.model;

import java.util.List;

public class ChatLog {
    private long id;
    private User user1;
    private User user2;
    private List<ChatMessage> chatLog;

    public ChatLog(long id, User user1, User user2, List<ChatMessage> chatLog) {
        this.id = id;
        this.user1 = user1;
        this.user2 = user2;
        this.chatLog = chatLog;
    }

    public ChatLog() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser1() {
        return user1;
    }

    public void setUser1(User user1) {
        this.user1 = user1;
    }

    public User getUser2() {
        return user2;
    }

    public void setUser2(User user2) {
        this.user2 = user2;
    }

    public List<ChatMessage> getChatLog() {
        return chatLog;
    }

    public void setChatLog(List<ChatMessage> chatLog) {
        this.chatLog = chatLog;
    }
}
