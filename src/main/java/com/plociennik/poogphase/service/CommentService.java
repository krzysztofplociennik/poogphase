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

    public Comment saveComment(final User author, final Post post, final Comment comment) {
        comment.setAuthor(author);
        comment.setPost(post);
        return commentRepository.save(comment);
    }

    public Optional<Comment> getComment(final long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public void removeComment(final long id) {
        Comment comment = commentRepository.findById(id).get();
        User author = comment.getAuthor();
        Post post = comment.getPost();
        author.getComments().remove(comment);
        userService.saveUser(author);
        post.getComments().remove(comment);
        postService.savePost(post.getAuthor(), post);
    }

    public Comment getByContent(final String content) {
        return commentRepository.findByContent(content);
    }
}
