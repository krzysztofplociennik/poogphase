package com.plociennik.poogphase.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "messages")
public class ChatMessage {
    private long id;
    private User author;
    private String recipient;
    private LocalDateTime dateTime;
    private String content;

    public ChatMessage(long id, User author, String recipient, LocalDateTime dateTime, String content) {
        this.id = id;
        this.author = author;
        this.recipient = recipient;
        this.dateTime = dateTime;
        this.content = content;
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
