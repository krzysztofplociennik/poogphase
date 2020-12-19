package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.Post;
import com.plociennik.poogphase.model.User;
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
    @Autowired
    private UserService userService;

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
    public void saveAndDeletePost2() {
        User user = userService.getUserByUsername("dummy");
        Post post = new Post();
        long initialSizeOfPostsInRep = postService.getAllPosts().size();
        long initialSizeOfPostsInUser = user.getPosts().size();
        post.setContent("save2PostContent");
        postService.savePost2(user, post);
        long searchedPostId = postService.getByContent("save2PostContent").getId();
        Assert.assertEquals(initialSizeOfPostsInRep + 1, postService.getAllPosts().size());
        Assert.assertEquals(initialSizeOfPostsInUser + 1, userService.getUserByUsername("dummy").getPosts().size());
        postService.removePost2(searchedPostId);
        Assert.assertEquals(initialSizeOfPostsInRep, postService.getAllPosts().size());
        Assert.assertEquals(initialSizeOfPostsInUser, userService.getUserByUsername("dummy").getPosts().size());
    }

    @Test
    public void getPost() {
        Post post = new Post();
        post.setContent("getContentPost");
        postService.savePost(post);

        long searchedPostId = postService.getByContent("getContentPost").getId();

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

        long searchedPostId = postService.getByContent("getAllContent").getId();
        long searchedPostId2 = postService.getByContent("getAllContent2").getId();

        Assert.assertEquals(initialSizeOfRepository + 2, postService.getAllPosts().size());

        postService.removePost(searchedPostId);
        postService.removePost(searchedPostId2);
    }

    @Test
    public void editPost2() {
        User user = userService.getUserByUsername("dummy");
        Post post = new Post();
        long initialSizeOfPostsInRep = postService.getAllPosts().size();
        long initialSizeOfPostsInUser = user.getPosts().size();
        post.setContent("save2PostContent");
        postService.savePost2(user, post);
        long searchedPostId = postService.getByContent("save2PostContent").getId();
        Assert.assertEquals(initialSizeOfPostsInRep + 1, postService.getAllPosts().size());
        Assert.assertEquals(initialSizeOfPostsInUser + 1, userService.getUserByUsername("dummy").getPosts().size());
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 12, 5), LocalTime.of(15, 11));
        post.setDateTime(dateTime);
        postService.savePost2(user, post);
        Assert.assertEquals(12, postService.getByContent("save2PostContent").getDateTime().getMonthValue());
        Assert.assertEquals(15, userService.getUserByUsername("dummy").getPosts().stream()
                .filter(post1 -> post1.getContent().equals("save2PostContent"))
                .findAny()
                .get()
                .getDateTime()
                .getHour());
        postService.removePost2(searchedPostId);
        Assert.assertEquals(initialSizeOfPostsInRep, postService.getAllPosts().size());
        Assert.assertEquals(initialSizeOfPostsInUser, userService.getUserByUsername("dummy").getPosts().size());
    }

    @Test
    public void showNumberOfRecords() {
        System.out.println("Number of records: " + postService.getAllPosts().size());
    }
}
