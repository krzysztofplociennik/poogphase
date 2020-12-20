package com.plociennik.poogphase.dto;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserDto {
    private long id;
    private String username;
    private String password;
    private String mail;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Set<String> friends;
    private Set<PostDto> posts;
    private Set<CommentDto> comments;
    private Set<ChatMessageDto> messages;
    private Map<UserDto, Set<ChatMessageDto>> chatLogs;

    public UserDto(long id, String username, String password, String mail, String firstName, String lastName,
                   LocalDate dateOfBirth, Set<String> friends, Set<PostDto> posts, Set<CommentDto> comments,
                   Set<ChatMessageDto> messages, Map<UserDto, Set<ChatMessageDto>> chatLogs) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.friends = friends;
        this.posts = posts;
        this.comments = comments;
        this.messages = messages;
        this.chatLogs = chatLogs;
    }

    public UserDto() {
        friends = new HashSet<>();
        posts = new HashSet<>();
        comments = new HashSet<>();
        messages = new HashSet<>();
        chatLogs = new HashMap<>();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getAge() {
        return Period.between(this.getDateOfBirth(), LocalDate.now()).getYears();
    }

    public Set<String> getFriends() {
        return friends;
    }

    public void setFriends(Set<String> friends) {
        this.friends = friends;
    }

    public Set<PostDto> getPosts() {
        return posts;
    }

    public void setPosts(Set<PostDto> posts) {
        this.posts = posts;
    }

    public Set<CommentDto> getComments() {
        return comments;
    }

    public void setComments(Set<CommentDto> comments) {
        this.comments = comments;
    }

    public Set<ChatMessageDto> getMessages() {
        return messages;
    }

    public void setMessages(Set<ChatMessageDto> messages) {
        this.messages = messages;
    }

    public Map<UserDto, Set<ChatMessageDto>> getChatLogs() {
        return chatLogs;
    }

    public void setChatLogs(Map<UserDto, Set<ChatMessageDto>> chatLogs) {
        this.chatLogs = chatLogs;
    }
}
