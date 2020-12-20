package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.Post;
import com.plociennik.poogphase.model.User;
import com.plociennik.poogphase.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserService userService;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Optional<Post> getPost(final long id) {
        return postRepository.findById(id);
    }

    public Post savePost(User author, Post post) {
        author.getPosts().add(post);
        post.setAuthor(author);
        return postRepository.save(post);
    }

    public void removePost(long id) {
        Post post = postRepository.findById(id).get();
        User author = post.getAuthor();
        author.getPosts().remove(post);
        userService.saveUser(author);
    }

    public Post getByContent(String content) {
        return postRepository.findByContent(content);
    }

}
