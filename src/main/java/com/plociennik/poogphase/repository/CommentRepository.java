package com.plociennik.poogphase.repository;

import com.plociennik.poogphase.model.Comment;
import com.plociennik.poogphase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Override
    List<Comment> findAll();

    Comment findByContent(String content);

    void deleteByContent(String content);
}
