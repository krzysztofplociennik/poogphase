package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.Post;
import com.plociennik.poogphase.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public List<Post> getAllPosts() {
        return repository.findAll();
    }

    public Post savePost(Post post) {
        return repository.save(post);
    }

    public Optional<Post> getPost(final long id) {
        return repository.findById(id);
    }

    public void removePost(long id) {
        repository.deleteById(id);
    }

    public Post findByContent(String content) {
        return repository.findByContent(content);
    }

}
