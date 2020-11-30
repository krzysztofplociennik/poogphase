package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.ChatMessage;
import com.plociennik.poogphase.model.Comment;
import com.plociennik.poogphase.repository.ChatMessageRepository;
import com.plociennik.poogphase.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Optional<Comment> getComment(final long id) {
        return commentRepository.findById(id);
    }

    public void removeComment(long id) {
        commentRepository.deleteById(id);
    }

    public Comment findByContentAndDateTime(String content) {
        return commentRepository.findByContent(content);
    }
}
