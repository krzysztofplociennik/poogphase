package com.plociennik.poogphase.mapper;

import com.plociennik.poogphase.dto.PostDto;
import com.plociennik.poogphase.model.Post;
import com.plociennik.poogphase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PostMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private CommentMapper commentMapper;

    public Post mapToPost(final PostDto postDto) {
        return new Post(
                postDto.getId(),
                userService.getUser(postDto.getAuthorId()).get(),
                postDto.getDateTime(),
                commentMapper.mapToCommentSet(postDto.getComments()),
                postDto.getContent());
    }

    public PostDto mapToPostDto(final Post post) {
        return new PostDto(
                post.getId(),
                post.getAuthor().getId(),
                post.getDateTime(),
                commentMapper.mapToCommentDtoSet(post.getComments()),
                post.getContent());
    }

    public Set<Post> mapToPostSet(final Set<PostDto> postsDto) {
        return postsDto.stream()
                .map(postDto -> new Post(
                        postDto.getId(),
                        userService.getUser(postDto.getAuthorId()).get(),
                        postDto.getDateTime(),
                        commentMapper.mapToCommentSet(postDto.getComments()),
                        postDto.getContent()))
                .collect(Collectors.toSet());
    }

    public Set<PostDto> mapToPostDtoSet(final Set<Post> posts) {
        return posts.stream()
                .map(post -> new PostDto(
                        post.getId(),
                        post.getAuthor().getId(),
                        post.getDateTime(),
                        commentMapper.mapToCommentDtoSet(post.getComments()),
                        post.getContent()))
                .collect(Collectors.toSet());
    }
}
