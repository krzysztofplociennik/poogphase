package com.plociennik.poogphase.service;

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
public class PostServiceTestSuite {
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
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
    public void savePost() {
        Post post = new Post();
        post.setContent("savePostContent");
        postService.savePost(post);

        long searchedPostId = postService.findByContent("savePostContent").getId();

        Assert.assertTrue(postService.getPost(searchedPostId).isPresent());

        postService.removePost(searchedPostId);
    }

    @Test
    public void getAllPosts() {
        long initialSizeOfRepository = postService.getAllPosts().size();
        Post post = new Post();
        Post post2 = new Post();
        post.setContent("getAllContent");
        post2.setContent("getAllContent2");
        postService.savePost(post);
        postService.savePost(post2);

        long searchedPostId = postService.findByContent("getAllContent").getId();
        long searchedPostId2 = postService.findByContent("getAllContent2").getId();

        Assert.assertEquals(initialSizeOfRepository + 2, postService.getAllPosts().size());

        postService.removePost(searchedPostId);
        postService.removePost(searchedPostId2);
    }

    @Test
    public void getPost() {
        Post post = new Post();
        post.setContent("getContentPost");
        postService.savePost(post);

        long searchedPostId = postService.findByContent("getContentPost").getId();

        Assert.assertTrue(postService.getPost(searchedPostId).isPresent());

        postService.removePost(searchedPostId);
    }

    @Test
    public void editPost() {
        Post post = new Post();
        post.setContent("editContentPost");
        postService.savePost(post);

        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 12, 1), LocalTime.of(11, 16));
        post.setDateTime(dateTime);
        postService.savePost(post);

        long searchedPostId = postService.findByContent("editContentPost").getId();

        Assert.assertEquals(1, postService.getPost(searchedPostId).get().getDateTime().getDayOfMonth());

        postService.removePost(searchedPostId);
    }

    @Test
    public void deletePost() {
        Post post = new Post();
        post.setContent("editContentPost");
        postService.savePost(post);

        long searchedPostId = postService.findByContent("editContentPost").getId();

        postService.removePost(searchedPostId);
    }

    @Test
    public void showNumberOfRecords() {
        System.out.println("Number of records: " + postService.getAllPosts().size());
    }
}
