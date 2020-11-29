package com.plociennik.poogphase.repository;

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
import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CommentRepositoryTestSuite {
    @Autowired
    private CommentRepository commentRepository;

    @Before
    public void initSomeData() {
        if (commentRepository.count() > 0) {
            commentRepository.deleteAll();
        }
        Comment comment1 = new Comment(1L,
                null,
                "This is my first comment",
                null,
                LocalDateTime.of(LocalDate.of(2020, 11, 28), LocalTime.of(18, 51)));
        Comment comment2 = new Comment(1L,
                null,
                "This is my second comment",
                null,
                LocalDateTime.of(LocalDate.of(2020, 11, 27), LocalTime.of(8, 2)));
        Comment comment3 = new Comment(1L,
                null,
                "This is my third comment",
                null,
                LocalDateTime.of(LocalDate.of(2020, 11, 30), LocalTime.of(14, 11)));
        Comment comment4 = new Comment();
        comment4.setContent("This is my fourth comment");

        commentRepository.saveAll(Arrays.asList(comment1, comment2, comment3, comment4));
    }

    @After
    public void cleanUpData() {
        commentRepository.deleteById(commentRepository.findByContent("This is my first comment").getId());
        commentRepository.deleteById(commentRepository.findByContent("This is my second comment").getId());
        commentRepository.deleteById(commentRepository.findByContent("This is my third comment").getId());
        commentRepository.deleteById(commentRepository.findByContent("This is my fourth comment").getId());
    }

    @Test
    public void saveComment() {
        //Given
        long sizeBeforeSaving = commentRepository.count();
        Comment dummyComment = new Comment();
        dummyComment.setContent("dummy");
        //When
        commentRepository.save(dummyComment);
        long dummyId = commentRepository.findByContent("dummy").getId();
        //Then
        Assert.assertEquals(sizeBeforeSaving + 1, commentRepository.count());
        //Clean up
        commentRepository.deleteById(dummyId);
        Assert.assertEquals(sizeBeforeSaving, commentRepository.count());
    }

    @Test
    public void getAllComments() {
        //Given
        long sizeBeforeSaving = commentRepository.count();
        Comment comment1 = new Comment();
        comment1.setContent("comment1");
        Comment comment2 = new Comment();
        comment2.setContent("comment2");
        //When
        commentRepository.saveAll(Arrays.asList(comment1, comment2));
        long dummyId1 = commentRepository.findByContent("comment1").getId();
        long dummyId2 = commentRepository.findByContent("comment2").getId();
        //Then
        Assert.assertEquals(sizeBeforeSaving + 2, commentRepository.count());
        //Clean up
        commentRepository.deleteById(dummyId1);
        commentRepository.deleteById(dummyId2);
    }

    @Test
    public void getComment() {
        //Given

        //When
        Comment searchedComment = commentRepository.findByContent("This is my second comment");
        //Then
        Assert.assertEquals(27, searchedComment.getDateTime().getDayOfMonth());
        //Clean up
    }

    @Test
    public void editComment() {
        //Given
        long sizeBeforeEditing = commentRepository.count();
        //When
        Comment searchedComment = commentRepository.findByContent("This is my first comment");
        searchedComment.setDateTime(LocalDateTime.of(LocalDate.of(2020, 11, 29),
                LocalTime.of(10, 45)));
        commentRepository.save(searchedComment);
        //Then
        Assert.assertEquals(sizeBeforeEditing, commentRepository.count());
        Assert.assertEquals(45, commentRepository.findByContent("This is my first comment").getDateTime().getMinute());
        //Clean up
    }

    @Test
    public void deletePost() {
        //Given
        long sizeBeforeDeleting = commentRepository.count();
        Comment comment = new Comment();
        comment.setContent("dummy");
        commentRepository.save(comment);
        //When
        long dummyId = commentRepository.findByContent("dummy").getId();
        commentRepository.deleteById(dummyId);
        //Then
        Assert.assertEquals(sizeBeforeDeleting, commentRepository.count());
    }
}
