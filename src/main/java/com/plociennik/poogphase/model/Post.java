package com.plociennik.poogphase.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Post {
    private long id;
    private User author;
    private String content;
    private LocalDateTime dateTime;
    private List<Comment> comments;

    public Post(long id, User author, String content, LocalDateTime dateTime, List<Comment> comments) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.dateTime = dateTime;
        this.comments = comments;
    }

    public Post() {
        comments = new ArrayList<>();
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
