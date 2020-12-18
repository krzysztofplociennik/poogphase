package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.Comment;
import com.plociennik.poogphase.model.Post;
import com.plociennik.poogphase.model.User;
import com.plociennik.poogphase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(final long id) {
        return userRepository.findById(id);
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void removeUser(final long id) {
        userRepository.deleteById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public void addFriend(User user, User friend) {
        user.getFriends().add(friend.getUsername());
        userRepository.save(user);
        friend.getFriends().add(user.getUsername());
        userRepository.save(friend);
    }

    public void deleteFriend(User user, User friend) {
        user.setFriends(user.getFriends().stream().filter(s -> !s.equals(friend.getUsername())).collect(Collectors.toSet()));
        userRepository.save(user);
        friend.setFriends(friend.getFriends().stream().filter(s -> !s.equals(user.getUsername())).collect(Collectors.toSet()));
        userRepository.save(friend);
    }

    public void createPost(User author, Post post) {
        author.getPosts().add(post);
        userRepository.save(author);
        post.setAuthor(author);
        postService.savePost(post);
    }

    public void deletePost(User author, Post post) {
        author.getPosts().remove(post);
        userRepository.save(author);
        long postId = post.getId();
        postService.removePost(postId);
    }

    public void createComment(User author, Post post, Comment comment) {
//        System.out.println(commentService.getAllComments().size());
//        author.getComments().add(comment);
//        saveUser(author);
//        System.out.println(commentService.getAllComments().size());
//        post.getComments().add(comment);
//        postService.savePost(post);
//        System.out.println(commentService.getAllComments().size());
//        commentService.saveComment(comment);
//        System.out.println(commentService.getAllComments().size());
//        commentService.saveComment(comment);
    }

    public void deleteComment(Comment comment) {
        commentService.removeComment(comment.getId());
    }

    public void sendMessage() {

    }
}
