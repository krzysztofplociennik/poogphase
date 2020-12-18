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
        Assert.assertEquals(initialCommentRepositorySize, commentRepository.count());
    }

    @Test
    public void saveComment() {
        commentRepository.save(new Comment(1L, null, "saveCommentContent", null, null));

        long searchedCommentId = commentRepository.findByContent("saveCommentContent").getId();

        Assert.assertEquals(initialCommentRepositorySize + 1, commentRepository.count());
        //Clean up
        commentRepository.deleteById(searchedCommentId);
    }

    @Test
    public void getAllComments() {
        commentRepository.save(new Comment(1L, null, "getAllContent", null, null));
        commentRepository.save(new Comment(1L, null, "getAllContent2", null, null));

        long searchedCommentId = commentRepository.findByContent("getAllContent").getId();
        long searchedCommentId2 = commentRepository.findByContent("getAllContent2").getId();

        Assert.assertEquals(initialCommentRepositorySize + 2, commentRepository.count());
        //Clean up
        commentRepository.deleteById(searchedCommentId);
        commentRepository.deleteById(searchedCommentId2);
    }

    @Test
    public void getComment() {
        commentRepository.save(new Comment(1L, null, "getCommentContent", null, null));

        long searchedCommentId = commentRepository.findByContent("getCommentContent").getId();

        Assert.assertTrue(commentRepository.findById(searchedCommentId).isPresent());
        //Clean up
        commentRepository.deleteById(searchedCommentId);
    }

    @Test
    public void editComment() {
        Comment comment = new Comment();
        comment.setContent("editCommentContent");
        commentRepository.save(comment);

        Assert.assertNull(commentRepository.findByContent("editCommentContent").getDateTime());

        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 11, 29), LocalTime.of(10, 45));
        comment.setDateTime(dateTime);
        commentRepository.save(comment);

        long searchedCommentId = commentRepository.findByContent("editCommentContent").getId();

        Assert.assertEquals(initialCommentRepositorySize + 1, commentRepository.count());
        Assert.assertEquals(2020, commentRepository.findById(searchedCommentId).get().getDateTime().getYear());
        //Clean up
        commentRepository.deleteById(searchedCommentId);
    }

    @Test
    public void deleteComment() {
        commentRepository.save(new Comment(1L, null, "deleteCommentContent", null, null));

        long searchedCommentId = commentRepository.findByContent("deleteCommentContent").getId();

        commentRepository.deleteById(searchedCommentId);
    }

    @Test
    public void showNumberOfRecords() {
        System.out.println("Number of records: " + commentRepository.count());
    }
}
