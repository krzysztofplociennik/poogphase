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
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostRepositoryTestSuite {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    @Before
    public void initSomeData() {
        Post post1 = new Post(1L,
                null,
                "This is my first post",
                LocalDateTime.of(LocalDate.of(2020, 11, 28), LocalTime.of(18, 51)),
                new ArrayList<>());
        Post post2 = new Post(1L,
                null,
                "This is my second post",
                LocalDateTime.of(LocalDate.of(2020, 11, 29), LocalTime.of(8, 2)),
                new ArrayList<>());
        Post post3 = new Post(1L,
                null,
                "This is my third post",
                LocalDateTime.of(LocalDate.of(2020, 11, 30), LocalTime.of(14, 11)),
                new ArrayList<>());
        Post post4 = new Post();
        post4.setContent("This is my fourth post");

        postRepository.saveAll(Arrays.asList(post1, post2, post3, post4));
    }

    @After
    public void cleanUpData() {
        postRepository.deleteById(postRepository.findByContent("This is my first post").getId());
        postRepository.deleteById(postRepository.findByContent("This is my second post").getId());
        postRepository.deleteById(postRepository.findByContent("This is my third post").getId());
        postRepository.deleteById(postRepository.findByContent("This is my fourth post").getId());
        System.out.println("There are " + postRepository.findAll().size() + " records now.");
    }

    @Test
    public void checkRepositorySize() {
    }

    @Test
    public void savePost() {
        //Given
        long sizeBeforeSaving = postRepository.count();
        Post dummyPost = new Post();
        dummyPost.setContent("dummy");
        //When
        postRepository.save(dummyPost);
        long dummyId = postRepository.findByContent("dummy").getId();
        //Then
        Assert.assertEquals(sizeBeforeSaving + 1, postRepository.count());
        //Clean up
        postRepository.deleteById(dummyId);
        Assert.assertEquals(sizeBeforeSaving, postRepository.count());
    }

    @Test
    public void getAllPosts() {
        //Given
        long sizeBeforeSaving = postRepository.count();
        Post post1 = new Post();
        post1.setContent("post1");
        Post post2 = new Post();
        post2.setContent("post2");
        //When
        postRepository.saveAll(Arrays.asList(post1, post2));
        long dummyId1 = postRepository.findByContent("post1").getId();
        long dummyId2 = postRepository.findByContent("post2").getId();
        //Then
        Assert.assertEquals(sizeBeforeSaving + 2, postRepository.count());
        //Clean up
        postRepository.deleteById(dummyId1);
        postRepository.deleteById(dummyId2);
    }

    @Test
    public void getPost() {
        //Given

        //When
        Post searchedPost = postRepository.findByContent("This is my second post");
        //Then
        Assert.assertEquals(29, searchedPost.getDateTime().getDayOfMonth());
        //Clean up
    }

    @Test
    public void editPost() {
        //Given
        long sizeBeforeEditing = postRepository.count();
        //When
        Post searchedPost = postRepository.findByContent("This is my first post");
        searchedPost.setDateTime(LocalDateTime.of(LocalDate.of(2020, 11, 29),
                LocalTime.of(10, 45)));
        postRepository.save(searchedPost);
        //Then
        Assert.assertEquals(sizeBeforeEditing, postRepository.count());
        Assert.assertEquals(10, postRepository.findByContent("This is my first post").getDateTime().getHour());
        //Clean up
    }

    @Test
    public void deletePost() {
        //Given
        long sizeBeforeDeleting = postRepository.count();
        Post post = new Post();
        post.setContent("dummy");
        postRepository.save(post);
        //When
        long dummyId = postRepository.findByContent("dummy").getId();
        postRepository.deleteById(dummyId);
        //Then
        Assert.assertEquals(sizeBeforeDeleting, postRepository.count());
    }

    @Test
    public void testIfCommentsAreNotNullUponCreatingNewPost() {
        //Given
        //When
        Post searchedPost = postRepository.findByContent("This is my fourth post");
        //Then
        Assert.assertNotEquals(null, searchedPost.getComments());
        //Clean up
    }
}


