package com.plociennik.poogphase.repository;

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
import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTestSuite {
    @Autowired
    private PostRepository postRepository;
    private long initialPostRepositorySize;

    @Before
    public void init() {
        initialPostRepositorySize = postRepository.count();
    }

    @After
    public void finalCheck() {
        Assert.assertEquals(initialPostRepositorySize, postRepository.count());
    }

    @Test
    public void savePost() {
        postRepository.save(new Post(1L, null, "savePostContent", null, new ArrayList<>()));

        long searchedPostId = postRepository.findByContent("savePostContent").getId();

        Assert.assertEquals(initialPostRepositorySize + 1, postRepository.count());
        //Clean up
        postRepository.deleteById(searchedPostId);
    }

    @Test
    public void getAllPosts() {
        postRepository.save(new Post(1L, null, "getAllPostsContent", null, new ArrayList<>()));
        postRepository.save(new Post(1L, null, "getAllPostsContent2", null, new ArrayList<>()));

        long searchedPostId = postRepository.findByContent("getAllPostsContent").getId();
        long searchedPostId2 = postRepository.findByContent("getAllPostsContent2").getId();

        Assert.assertEquals(initialPostRepositorySize + 2, postRepository.count());
        //Clean up
        postRepository.deleteById(searchedPostId);
        postRepository.deleteById(searchedPostId2);
    }

    @Test
    public void getPost() {
        postRepository.save(new Post(1L, null, "getPostContent", null, new ArrayList<>()));

        long searchedPostId = postRepository.findByContent("getPostContent").getId();

        Assert.assertTrue(postRepository.findById(searchedPostId).isPresent());
        //Clean up
        postRepository.deleteById(searchedPostId);
    }

    @Test
    public void editPost() {
        postRepository.save(new Post(1L, null, "editPostContent", null, new ArrayList<>()));

        Assert.assertEquals(initialPostRepositorySize + 1, postRepository.count());
        Assert.assertNull(postRepository.findByContent("editPostContent").getDateTime());

        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 11, 29), LocalTime.of(10, 45));
        Post searchedPost = postRepository.findByContent("editPostContent");
        searchedPost.setDateTime(dateTime);
        postRepository.save(searchedPost);

        Assert.assertEquals(initialPostRepositorySize + 1, postRepository.count());
        Assert.assertEquals(10, postRepository.findById(searchedPost.getId()).get().getDateTime().getHour());
        //Clean up
        postRepository.deleteById(searchedPost.getId());
    }

    @Test
    public void deletePost() {
        postRepository.save(new Post(1L, null, "deletePostContent", null, new ArrayList<>()));

        long searchedPostId = postRepository.findByContent("deletePostContent").getId();

        postRepository.deleteById(searchedPostId);
    }

    @Test
    public void testIfCommentsAreNotNullUponCreatingNewPost() {
        postRepository.save(new Post(1L, null, "notNullContent", null, new ArrayList<>()));

        long searchedPostId = postRepository.findByContent("notNullContent").getId();

        Assert.assertNotNull(postRepository.findById(searchedPostId).get().getComments());
        //Clean up
        postRepository.deleteById(searchedPostId);
    }

    @Test
    public void showNumberOfRecords() {
        System.out.println("There are " + postRepository.findAll().size() + " records now.");
    }
}


