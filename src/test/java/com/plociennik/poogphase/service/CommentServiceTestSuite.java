package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.Comment;
import com.plociennik.poogphase.model.Post;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentServiceTestSuite {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PostService postService;
    long initialPostRepositorySize;
    long initialCommentRepositorySize;

    @Before
    public void init() {
        initialPostRepositorySize = postService.getAllPosts().size();
        initialCommentRepositorySize = commentService.getAllComments().size();
    }

    @After
    public void finalCheck() {
        Assert.assertEquals(initialPostRepositorySize, postService.getAllPosts().size());
        Assert.assertEquals(initialCommentRepositorySize, commentService.getAllComments().size());
    }

    @Test
    public void saveComment() {
        Post post = new Post();
        post.setContent("saveContent1Post");
        postService.savePost(post);
        Comment comment = new Comment();
        comment.setContent("saveContent1Comment");
        comment.setPost(post);
        commentService.saveComment(comment);
        //Getting id
        long searchedPostId = postService.findByContent("saveContent1Post").getId();
        long searchedCommentId = commentService.findByContent("saveContent1Comment").getId();

        Assert.assertTrue(commentService.getComment(searchedCommentId).isPresent());
        //Clean up
        commentService.removeComment(searchedCommentId);
        postService.removePost(searchedPostId);
    }

    @Test
    public void getAllComments() {
        long initialSizeOfRepository = commentService.getAllComments().size();
        Post post = new Post();
        Post post2 = new Post();
        post.setContent("getAllContentPost");
        post2.setContent("getAllContentPost2");
        postService.savePost(post);
        postService.savePost(post2);
        Comment comment = new Comment();
        Comment comment2 = new Comment();
        comment.setContent("getAllContentComment");
        comment2.setContent("getAllContentComment2");
        comment.setPost(post);
        comment2.setPost(post2);
        commentService.saveComment(comment);
        commentService.saveComment(comment2);

        long searchedCommentId = commentService.findByContent("getAllContentComment").getId();
        long searchedCommentId2 = commentService.findByContent("getAllContentComment2").getId();
        long searchedPostId = postService.findByContent("getAllContentPost").getId();
        long searchedPostId2 = postService.findByContent("getAllContentPost2").getId();

        Assert.assertEquals(initialSizeOfRepository + 2, commentService.getAllComments().size());

        commentService.removeComment(searchedCommentId);
        commentService.removeComment(searchedCommentId2);
        postService.removePost(searchedPostId);
        postService.removePost(searchedPostId2);
    }

    @Test
    public void getComment() {
        Post post = new Post();
        post.setContent("getContentPost");
        postService.savePost(post);
        Comment comment = new Comment();
        comment.setContent("getContentComment");
        comment.setPost(post);
        commentService.saveComment(comment);

        long searchedCommentId = commentService.findByContent("getContentComment").getId();
        long searchedPostId = postService.findByContent("getContentPost").getId();

        Assert.assertTrue(commentService.getComment(searchedCommentId).isPresent());

        commentService.removeComment(searchedCommentId);
        postService.removePost(searchedPostId);
    }

    @Test
    public void editComment() {
        Post post = new Post();
        post.setContent("editContentPost");
        postService.savePost(post);
        Comment comment = new Comment();
        comment.setContent("editContentComment");
        comment.setPost(post);
        commentService.saveComment(comment);

        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 12, 1), LocalTime.of(11, 16));
        comment.setDateTime(dateTime);
        commentService.saveComment(comment);

        long searchedCommentId = commentService.findByContent("editContentComment").getId();
        long searchedPostId = postService.findByContent("editContentPost").getId();

        Assert.assertEquals(12, commentService.getComment(searchedCommentId).get().getDateTime().getMonthValue());

        commentService.removeComment(searchedCommentId);
        postService.removePost(searchedPostId);
    }

    @Test
    public void deleteComment() {
        Post post = new Post();
        post.setContent("removeContentPost");
        postService.savePost(post);
        Comment comment = new Comment();
        comment.setContent("removeContentComment");
        comment.setPost(post);
        commentService.saveComment(comment);

        long searchedCommentId = commentService.findByContent("removeContentComment").getId();
        long searchedPostId = postService.findByContent("removeContentPost").getId();

        commentService.removeComment(searchedCommentId);
        postService.removePost(searchedPostId);
    }

    @Test
    public void showNumberOfRecords() {
        System.out.println("Number of records: " + commentService.getAllComments().size());
    }
}
