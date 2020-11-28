package com.plociennik.poogphase.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class ChatMessage {
    private long id;
    private User author;
    private String recipient;
    private String content;
    private LocalDateTime dateTime;
    private ChatLog chatLog;

    public ChatMessage(long id, User author, String recipient, String content, LocalDateTime dateTime, ChatLog chatLog) {
        this.id = id;
        this.author = author;
        this.recipient = recipient;
        this.content = content;
        this.dateTime = dateTime;
        this.chatLog = chatLog;
    }

    public ChatMessage() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
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

    @ManyToOne
    public ChatLog getChatLog() {
        return chatLog;
    }

    public void setChatLog(ChatLog chatLog) {
        this.chatLog = chatLog;
    }
}
