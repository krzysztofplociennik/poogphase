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
import java.util.Arrays;

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
        Post post = new Post();
        post.setContent("saveContent");
        postRepository.save(post);

        long searchedPostId = postRepository.findByContent("saveContent").getId();

        Assert.assertEquals(initialPostRepositorySize + 1, postRepository.count());
        //Clean up
        postRepository.deleteById(searchedPostId);
    }

    @Test
    public void getAllPosts() {
        Post post1 = new Post();
        post1.setContent("getAllContent");
        Post post2 = new Post();
        post2.setContent("getAllContent2");
        postRepository.saveAll(Arrays.asList(post1, post2));

        long searchedPostId = postRepository.findByContent("getAllContent").getId();
        long searchedPostId2 = postRepository.findByContent("getAllContent2").getId();

        Assert.assertEquals(initialPostRepositorySize + 2, postRepository.count());
        //Clean up
        postRepository.deleteById(searchedPostId);
        postRepository.deleteById(searchedPostId2);
    }

    @Test
    public void getPost() {
        Post post = new Post();
        post.setContent("getContent");
        postRepository.save(post);

        long searchedPostId = postRepository.findByContent("getContent").getId();

        Assert.assertTrue(postRepository.findById(searchedPostId).isPresent());
        //Clean up
        postRepository.deleteById(searchedPostId);
    }

    @Test
    public void editPost() {
        Post post = new Post();
        post.setContent("editContent");
        postRepository.save(post);

        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 11, 29), LocalTime.of(10, 45));
        post.setDateTime(dateTime);
        postRepository.save(post);

        long searchedPostId = postRepository.findByContent("editContent").getId();

        Assert.assertEquals(initialPostRepositorySize + 1, postRepository.count());
        Assert.assertEquals(10, postRepository.findById(searchedPostId).get().getDateTime().getHour());
        //Clean up
        postRepository.deleteById(searchedPostId);
    }

    @Test
    public void deletePost() {
        Post post = new Post();
        post.setContent("deleteContent");
        postRepository.save(post);

        long searchedPostId = postRepository.findByContent("deleteContent").getId();

        postRepository.deleteById(searchedPostId);
    }

    @Test
    public void testIfCommentsAreNotNullUponCreatingNewPost() {
        Post post = new Post();
        post.setContent("notNullContent");
        postRepository.save(post);

        long searchedPostId = postRepository.findByContent("notNullContent").getId();

        Assert.assertNotEquals(null, postRepository.findById(searchedPostId).get().getComments());
        //Clean up
        postRepository.deleteById(searchedPostId);
    }

    @Test
    public void showNumberOfRecords() {
        System.out.println("There are " + postRepository.findAll().size() + " records now.");
    }
}


