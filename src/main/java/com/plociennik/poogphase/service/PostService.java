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

    public Post savePost(final User author, final Post post) {
        author.getPosts().add(post);
        post.setAuthor(author);
        return postRepository.save(post);
    }

    public Optional<Post> getPost(final long id) {
        return postRepository.findById(id);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void removePost(final long id) {
        Post post = postRepository.findById(id).get();
        User author = post.getAuthor();
        author.getPosts().remove(post);
        userService.saveUser(author);
    }

    public Post getByContent(final String content) {
        return postRepository.findByContent(content);
    }

}
