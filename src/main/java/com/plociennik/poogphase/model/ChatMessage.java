package com.plociennik.poogphase.model;

import java.time.LocalDateTime;

public class ChatMessage {
    private long id;
    private User author;
    private User recipient;
    private String content;
    private LocalDateTime dateTime;

    public ChatMessage(long id, User author, User recipient, String content, LocalDateTime dateTime) {
        this.id = id;
        this.author = author;
        this.recipient = recipient;
        this.content = content;
        this.dateTime = dateTime;
    }

    public ChatMessage() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
