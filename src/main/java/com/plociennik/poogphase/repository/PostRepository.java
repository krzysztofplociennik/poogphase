package com.plociennik.poogphase.repository;

import com.plociennik.poogphase.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Override
    List<Post> findAll();

    Post findByContent(String content);
}
