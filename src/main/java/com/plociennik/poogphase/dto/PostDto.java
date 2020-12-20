package com.plociennik.poogphase.dto;

import com.plociennik.poogphase.model.Comment;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class PostDto {
    private long id;
    private long authorId;
    private LocalDateTime dateTime;
    private Set<CommentDto> comments;
    private String content;

    public PostDto(long id, long authorId, LocalDateTime dateTime, Set<CommentDto> comments, String content) {
        this.id = id;
        this.authorId = authorId;
        this.dateTime = dateTime;
        this.comments = comments;
        this.content = content;
    }

    public PostDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(long authorId) {
        this.authorId = authorId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Set<CommentDto> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDto> comments) {
        this.comments = comments;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
