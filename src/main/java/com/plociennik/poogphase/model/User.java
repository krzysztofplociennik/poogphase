package com.plociennik.poogphase.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class User {
    private long id;
    private String username;
    private String password;
    private String mail;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private List<User> friends;
    private Map<User, ChatLog> chatArchive;
    private List<Post> posts;
    private List<Comment> comments;

    public User(long id, String username, String password, String mail, String firstName, String lastName, LocalDate dateOfBirth, List<User> friends, Map<User, ChatLog> chatArchive, List<Post> posts, List<Comment> comments) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.friends = friends;
        this.chatArchive = chatArchive;
        this.posts = posts;
        this.comments = comments;
    }

    public User() {
        friends = new ArrayList<>();
        chatArchive = new HashMap<>();
        posts = new ArrayList<>();
        comments = new ArrayList<>();
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

    public int getAge() {
        return Period.between(this.getDateOfBirth(), LocalDate.now()).getYears();
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) {
        this.friends = friends;
    }

    public Map<User, ChatLog> getChatArchive() {
        return chatArchive;
    }

    public void setChatArchive(Map<User, ChatLog> chatArchive) {
        this.chatArchive = chatArchive;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId() == user.getId() &&
                Objects.equals(getUsername(), user.getUsername()) &&
                Objects.equals(getPassword(), user.getPassword()) &&
                Objects.equals(getMail(), user.getMail()) &&
                Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                Objects.equals(getDateOfBirth(), user.getDateOfBirth()) &&
                Objects.equals(getFriends(), user.getFriends()) &&
                Objects.equals(chatArchive, user.chatArchive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getMail(), getFirstName(), getLastName(), getDateOfBirth());
    }
}
