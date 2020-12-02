package com.plociennik.poogphase.repository;

import com.plociennik.poogphase.model.Comment;
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
public class CommentRepositoryTestSuite {
    @Autowired
    private CommentRepository commentRepository;
    private long initialCommentRepositorySize;

    @Before
    public void init() {
        initialCommentRepositorySize = commentRepository.count();
    }

    @After
    public void finalCheck() {
        System.out.println("Number of records: " + commentRepository.count());
    }

    @Test
    public void saveComment() {
        //Given
        Comment comment = new Comment();
        comment.setContent("saveCommentContent");
        //When
        commentRepository.save(comment);
        long searchedCommentId = commentRepository.findByContent("saveCommentContent").getId();
        //Then
        Assert.assertEquals(initialCommentRepositorySize + 1, commentRepository.count());
        //Clean up
        commentRepository.deleteById(searchedCommentId);
    }

    @Test
    public void getAllComments() {
        //Given
        Comment comment1 = new Comment();
        comment1.setContent("getAllContent");
        Comment comment2 = new Comment();
        comment2.setContent("getAllContent2");
        //When
        commentRepository.saveAll(Arrays.asList(comment1, comment2));
        long searchedCommentId = commentRepository.findByContent("getAllContent").getId();
        long searchedCommentId2 = commentRepository.findByContent("getAllContent2").getId();
        //Then
        Assert.assertEquals(initialCommentRepositorySize + 2, commentRepository.count());
        //Clean up
        commentRepository.deleteById(searchedCommentId);
        commentRepository.deleteById(searchedCommentId2);
    }

    @Test
    public void getComment() {
        //Given
        Comment comment = new Comment();
        comment.setContent("getContent");
        commentRepository.save(comment);
        //When
        long searchedCommentId = commentRepository.findByContent("getContent").getId();
        //Then
        Assert.assertTrue(commentRepository.findById(searchedCommentId).isPresent());
        //Clean up
        commentRepository.deleteById(searchedCommentId);
    }

    @Test
    public void editComment() {
        //Given
        //When
        Comment comment = new Comment();
        comment.setContent("editContent");
        commentRepository.save(comment);

        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 11, 29), LocalTime.of(10, 45));
        comment.setDateTime(dateTime);
        commentRepository.save(comment);

        long searchedCommentId = commentRepository.findByContent("editContent").getId();
        //Then
        Assert.assertEquals(initialCommentRepositorySize + 1, commentRepository.count());
        Assert.assertEquals(2020, commentRepository.findById(searchedCommentId).get().getDateTime().getYear());
        //Clean up
        commentRepository.deleteById(searchedCommentId);
    }

    @Test
    public void deleteComment() {
        //Given
        Comment comment = new Comment();
        comment.setContent("deleteContent");
        commentRepository.save(comment);
        //When
        long searchedCommentId = commentRepository.findByContent("deleteContent").getId();
        commentRepository.deleteById(searchedCommentId);
        //Then
    }

    @Test
    public void showNumberOfRecords() {
        System.out.println("Number of records: " + commentRepository.count());
    }
}
