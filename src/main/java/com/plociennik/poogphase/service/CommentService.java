package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.Comment;
import com.plociennik.poogphase.model.Post;
import com.plociennik.poogphase.model.User;
import com.plociennik.poogphase.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment saveComment(User user, Post post, Comment comment) {
        comment.getPost().getComments().add(comment);
        comment.getPost().setComments(comment.getPost().getComments());
        return commentRepository.save(comment);
    }

    public Comment saveComment2(User author, Post post, Comment comment) {
//        author.getComments().add(comment);
//        post.getComments().add(comment);
        comment.setAuthor(author);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public Optional<Comment> getComment(final long id) {
        return commentRepository.findById(id);
    }

    public void removeComment(long id) {
        commentRepository.deleteById(id);
    }

    public void removeComment2(long id) {
        Comment comment = commentRepository.findById(id).get();
        User author = comment.getAuthor();
        Post post = comment.getPost();
        author.getComments().remove(comment);
        userService.saveUser(author);
        post.getComments().remove(comment);
        postService.savePost2(post.getAuthor(), post);
    }

    public Comment getByContent(String content) {
        return commentRepository.findByContent(content);
    }
}
