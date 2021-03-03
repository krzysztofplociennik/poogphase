package com.plociennik.poogphase.model;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@Entity(name = "users")
public class User {
    private long id;
    private String username;
    private String password;
    private String mail;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private Set<String> friends;
    private List<Post> posts;
    private List<Comment> comments;
    private List<ChatMessage> messages;

    public User(long id, String username, String password, String mail, String firstName, String lastName,
                LocalDate dateOfBirth, Set<String> friends, List<Post> posts, List<Comment> comments,
                List<ChatMessage> messages) {
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
    }

    public User() {
        friends = new HashSet<>();
        posts = new ArrayList<>();
        comments = new ArrayList<>();
        messages = new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Column
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Transient
    public int getAge() {
        return Period.between(this.getDateOfBirth(), LocalDate.now()).getYears();
    }

    @ElementCollection
    @LazyCollection(LazyCollectionOption.FALSE)
    public Set<String> getFriends() {
        return friends;
    }

    public void setFriends(Set<String> friends) {
        this.friends = friends;
    }

    @OneToMany(
            fetch = FetchType.EAGER,
            targetEntity = Post.class,
            mappedBy = "author",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    @OneToMany(
            targetEntity = Comment.class,
            mappedBy = "author",
            orphanRemoval = true,
            cascade = CascadeType.ALL
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @OneToMany(
            targetEntity = ChatMessage.class,
            mappedBy = "author",
            cascade = CascadeType.REMOVE
    )
    @LazyCollection(LazyCollectionOption.FALSE)
    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
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
                Objects.equals(getFriends(), user.getFriends());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getUsername(), getPassword(), getMail(), getFirstName(), getLastName(), getDateOfBirth(), (int)(Math.random() * 9999) + 1);
    }
}
