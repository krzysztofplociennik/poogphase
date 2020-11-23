package com.plociennik.poogphase.model;

import java.time.LocalDateTime;

public class Post {
    private long id;
    private User author;
    private String content;
    private LocalDateTime dateTime;

    public Post(long id, User author, String content, LocalDateTime dateTime) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.dateTime = dateTime;
    }

    public Post() {
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
