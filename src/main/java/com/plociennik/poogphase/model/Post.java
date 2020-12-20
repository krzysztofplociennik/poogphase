package com.plociennik.poogphase.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "posts")
public class Post {
    private long id;
    private User author;
    private LocalDateTime dateTime;
    private List<Comment> comments;
    private String content;

    public Post(long id, User author, LocalDateTime dateTime, List<Comment> comments, String content) {
        this.id = id;
        this.author = author;
        this.dateTime = dateTime;
        this.comments = comments;
        this.content = content;
    }

    public Post() {
        comments = new ArrayList<>();
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

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Comment.class,
            mappedBy = "post",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
