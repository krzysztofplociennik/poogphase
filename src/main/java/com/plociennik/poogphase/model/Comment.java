package com.plociennik.poogphase.model;

import java.time.LocalDateTime;

public class Comment {
    private long id;
    private User author;
    private String content;
    private Post post;
    private LocalDateTime dateTime;

    public Comment(long id, User author, String content, Post post, LocalDateTime dateTime) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.post = post;
        this.dateTime = dateTime;
    }

    public Comment() {
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

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
