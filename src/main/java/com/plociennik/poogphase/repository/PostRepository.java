package com.plociennik.poogphase.repository;

import com.plociennik.poogphase.model.Post;
import com.plociennik.poogphase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    @Override
    List<Post> findAll();
}
