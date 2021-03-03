package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.Comment;
import com.plociennik.poogphase.model.Post;
import com.plociennik.poogphase.model.User;
import com.plociennik.poogphase.repository.CommentRepository;
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
    private CommentRepository commentRepository;
    @Autowired
    private UserService userService;
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
    public void saveAndDeleteComment() {
        //User and post setup
        User user = userService.getUserByUsername("goofy");
        Post post = new Post();
        long initialPostsSize = postService.getAllPosts().size();
        long initialPostListSize = user.getPosts().size();
        long initialCommentsInUserSize = user.getComments().size();
        long initialCommentsInPostSize = post.getComments().size();
        post.setAuthor(user);
        post.setContent("save2PostContent");
        postService.savePost(post);
        long searchedPostId = postService.getByContent("save2PostContent").getId();
        //Saving post
        Assert.assertEquals(initialPostsSize + 1, postService.getAllPosts().size());
        Assert.assertEquals(initialPostListSize + 1, userService.getUserByUsername("goofy").getPosts().size());
        //Comment setup
        Comment comment = new Comment();
        long initialSizeOfCommentsInRep = commentService.getAllComments().size();
        comment.setAuthor(user);
        comment.setPost(post);
        comment.setContent("save2CommentContent");
        commentService.saveComment(comment);
        long searchedCommentId = commentService.getByContent("save2CommentContent").getId();
        //Saving comment
        Assert.assertEquals(initialSizeOfCommentsInRep + 1, commentService.getAllComments().size());
        Assert.assertEquals(initialCommentsInUserSize + 1, userService.getUserByUsername("goofy").getComments().size());
        Assert.assertEquals(initialCommentsInPostSize + 1, postService.getByContent("save2PostContent").getComments().size());
        //Removing comment
        commentService.removeComment(searchedCommentId);
        Assert.assertEquals(initialSizeOfCommentsInRep, commentService.getAllComments().size());
        Assert.assertEquals(initialCommentsInUserSize, userService.getUserByUsername("goofy").getComments().size());
        Assert.assertEquals(initialCommentsInPostSize, postService.getByContent("save2PostContent").getComments().size());
        //Removing post
        postService.removePost(searchedPostId);
        Assert.assertEquals(initialPostsSize, postService.getAllPosts().size());
        Assert.assertEquals(initialPostListSize, userService.getUserByUsername("goofy").getPosts().size());
    }

    @Test
    public void getComment() {
        User user = userService.getUserByUsername("goofy");
        Post post = new Post();
        post.setAuthor(user);
        post.setContent("getContentPost");
        postService.savePost(post);
        Comment comment = new Comment();
        comment.setContent("getContentComment");
        comment.setAuthor(user);
        comment.setPost(post);
        commentService.saveComment(comment);

        long searchedCommentId = commentService.getByContent("getContentComment").getId();
        long searchedPostId = postService.getByContent("getContentPost").getId();

        Assert.assertTrue(commentService.getComment(searchedCommentId).isPresent());

        commentService.removeComment(searchedCommentId);
        postService.removePost(searchedPostId);
    }

    @Test
    public void getAllComments() {
        User user = userService.getUserByUsername("goofy");
        long initialSizeOfRepository = commentService.getAllComments().size();
        Post post = new Post();
        Post post2 = new Post();
        post.setAuthor(user);
        post2.setAuthor(user);
        post.setContent("getAllContentPost");
        post2.setContent("getAllContentPost2");
        postService.savePost(post);
        postService.savePost(post2);
        Comment comment = new Comment();
        Comment comment2 = new Comment();
        comment.setContent("getAllContentComment");
        comment2.setContent("getAllContentComment2");
        comment.setAuthor(user);
        comment2.setAuthor(user);
        comment.setPost(post);
        comment2.setPost(post2);
        commentService.saveComment(comment);
        commentService.saveComment(comment2);

        long searchedCommentId = commentService.getByContent("getAllContentComment").getId();
        long searchedCommentId2 = commentService.getByContent("getAllContentComment2").getId();
        long searchedPostId = postService.getByContent("getAllContentPost").getId();
        long searchedPostId2 = postService.getByContent("getAllContentPost2").getId();

        Assert.assertEquals(initialSizeOfRepository + 2, commentService.getAllComments().size());

        commentService.removeComment(searchedCommentId);
        commentService.removeComment(searchedCommentId2);
        postService.removePost(searchedPostId);
        postService.removePost(searchedPostId2);
    }

    @Test
    public void editComment() {
        //User and post setup
        User user = userService.getUserByUsername("goofy");
        Post post = new Post();
        long initialPostsSize = postService.getAllPosts().size();
        long initialPostListSize = user.getPosts().size();
        long initialCommentsInUserSize = user.getComments().size();
        long initialCommentsInPostSize = post.getComments().size();
        post.setAuthor(user);
        post.setContent("save2PostContent");
        postService.savePost(post);
        long searchedPostId = postService.getByContent("save2PostContent").getId();
        //Saving post
        Assert.assertEquals(initialPostsSize + 1, postService.getAllPosts().size());
        Assert.assertEquals(initialPostListSize + 1, userService.getUserByUsername("goofy").getPosts().size());
        //Comment setup
        Comment comment = new Comment();
        long initialSizeOfCommentsInRep = commentService.getAllComments().size();
        comment.setContent("save2CommentContent");
        comment.setAuthor(user);
        comment.setPost(post);
        commentService.saveComment(comment);
        long searchedCommentId = commentService.getByContent("save2CommentContent").getId();
        //Saving comment
        Assert.assertEquals(initialSizeOfCommentsInRep + 1, commentService.getAllComments().size());
        Assert.assertEquals(initialCommentsInUserSize + 1, userService.getUserByUsername("goofy").getComments().size());
        Assert.assertEquals(initialCommentsInPostSize + 1, postService.getByContent("save2PostContent").getComments().size());
        Assert.assertNull(commentService.getByContent("save2CommentContent").getDateTime());

        Comment searchedComment = commentService.getByContent("save2CommentContent");
        LocalDateTime dateTime = LocalDateTime.of(LocalDate.of(2020, 12, 17), LocalTime.of(16, 52));
        searchedComment.setDateTime(dateTime);
        commentService.saveComment(searchedComment);

        Assert.assertEquals(initialSizeOfCommentsInRep + 1, commentService.getAllComments().size());
        Assert.assertEquals(initialCommentsInUserSize + 1, userService.getUserByUsername("goofy").getComments().size());
        Assert.assertEquals(initialCommentsInPostSize + 1, postService.getByContent("save2PostContent").getComments().size());
        Assert.assertEquals(2020, commentService.getByContent("save2CommentContent").getDateTime().getYear());
        //Removing comment
        commentService.removeComment(searchedCommentId);
        Assert.assertEquals(initialSizeOfCommentsInRep, commentService.getAllComments().size());
        Assert.assertEquals(initialCommentsInUserSize, userService.getUserByUsername("goofy").getComments().size());
        Assert.assertEquals(initialCommentsInPostSize, postService.getByContent("save2PostContent").getComments().size());
        //Removing post
        postService.removePost(searchedPostId);
        Assert.assertEquals(initialPostsSize, postService.getAllPosts().size());
        Assert.assertEquals(initialPostListSize, userService.getUserByUsername("goofy").getPosts().size());
    }

    @Test
    public void showNumberOfRecords() {
        System.out.println("Number of records: " + commentService.getAllComments().size());
    }

    @Test
    public void saveData() {
//        User user = userService.getUserByUsername("dummy");
//        Post post = postService.getPost(1112L).get();
//        Comment comment = new Comment();
//        comment.setAuthor(user);
//        comment.setPost(post);
//        comment.setContent("Hey there goofy!");
//        commentService.saveComment(comment);
    }
}
