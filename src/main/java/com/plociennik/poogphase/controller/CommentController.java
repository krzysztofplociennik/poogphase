package com.plociennik.poogphase.controller;

import com.plociennik.poogphase.exceptions.CommentNotFound;
import com.plociennik.poogphase.mapper.CommentMapper;
import com.plociennik.poogphase.model.dto.CommentDto;
import com.plociennik.poogphase.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentMapper commentMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getComments")
    public List<CommentDto> getAllComments() {
        return commentMapper.mapToCommentDtoSet(commentService.getAllComments());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getComment")
    public CommentDto getComment(@RequestParam Long commentId) throws CommentNotFound {
        return commentMapper.mapToCommentDto(commentService.getComment(commentId).orElseThrow(CommentNotFound::new));
    }

    @RequestMapping(method = RequestMethod.POST, value = "createComment")
    public void createComment(@RequestBody CommentDto commentDto) {
        commentService.saveComment(commentMapper.mapToComment(commentDto));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateComment")
    public CommentDto updateComment(@RequestBody CommentDto commentDto) {
        return commentMapper.mapToCommentDto(commentService.saveComment(commentMapper.mapToComment(commentDto)));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteComment")
    public void deleteComment(@RequestParam Long commentId) {
        commentService.removeComment(commentId);
    }
}
