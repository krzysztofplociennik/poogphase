package com.plociennik.poogphase.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "comments")
public class Comment {
    private long id;
    private User author;
    private Post post;
    private LocalDateTime dateTime;
    private String content;

    public Comment(long id, User author, Post post, LocalDateTime dateTime, String content) {
        this.id = id;
        this.author = author;
        this.post = post;
        this.dateTime = dateTime;
        this.content = content;
    }

    public Comment() {
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

    @ManyToOne
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
