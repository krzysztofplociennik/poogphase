package com.plociennik.poogphase.controller;

import com.plociennik.poogphase.exceptions.PostNotFound;
import com.plociennik.poogphase.mapper.PostMapper;
import com.plociennik.poogphase.model.dto.PostDto;
import com.plociennik.poogphase.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private PostMapper postMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getPosts")
    public List<PostDto> getAllPosts() {
        return postMapper.mapToPostDtoList(postService.getAllPosts());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getPost")
    public PostDto getPost(@RequestParam Long postId) throws PostNotFound {
        return postMapper.mapToPostDto(postService.getPost(postId).orElseThrow(PostNotFound::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createPost")
    public void createPost(@RequestBody PostDto postDto) {
        postService.savePost(postMapper.mapToPost(postDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updatePost")
    public PostDto updateUser(@RequestBody PostDto postDto) {
        return postMapper.mapToPostDto(postService.savePost(postMapper.mapToPost(postDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deletePost")
    public void deletePost(@RequestParam Long postId) {
        postService.removePost(postId);
    }
}
