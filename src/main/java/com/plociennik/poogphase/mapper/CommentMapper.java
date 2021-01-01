package com.plociennik.poogphase.mapper;

import com.plociennik.poogphase.model.dto.CommentDto;
import com.plociennik.poogphase.model.Comment;
import com.plociennik.poogphase.service.PostService;
import com.plociennik.poogphase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class CommentMapper {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;

    public Comment mapToComment(final CommentDto commentDto) {
        return new Comment(
                commentDto.getId(),
                userService.getUser(commentDto.getAuthorId()).get(),
                postService.getPost(commentDto.getPostId()).get(),
                commentDto.getDateTime(),
                commentDto.getContent());
    }

    public CommentDto mapToCommentDto(final Comment comment) {
        return new CommentDto(
                comment.getId(),
                comment.getAuthor().getId(),
                comment.getPost().getId(),
                comment.getDateTime(),
                comment.getContent());
    }

    public List<Comment> mapToCommentSet(final List<CommentDto> commentsDto) {
        return commentsDto.stream()
                .map(commentDto -> new Comment(
                        commentDto.getId(),
                        userService.getUser(commentDto.getAuthorId()).get(),
                        postService.getPost(commentDto.getPostId()).get(),
                        commentDto.getDateTime(),
                        commentDto.getContent()))
                .collect(Collectors.toList());
    }

    public List<CommentDto> mapToCommentDtoSet(final List<Comment> comments) {
        return comments.stream()
                .map(comment -> new CommentDto(
                        comment.getId(),
                        comment.getAuthor().getId(),
                        comment.getPost().getId(),
                        comment.getDateTime(),
                        comment.getContent()))
                .collect(Collectors.toList());
    }
}
