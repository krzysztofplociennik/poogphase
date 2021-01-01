package com.plociennik.poogphase.repository.other;

import com.plociennik.poogphase.model.ChatMessage;
import com.plociennik.poogphase.model.Post;
import com.plociennik.poogphase.repository.*;
import com.plociennik.poogphase.service.ChatMessageService;
import com.plociennik.poogphase.service.CommentService;
import com.plociennik.poogphase.service.PostService;
import com.plociennik.poogphase.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VariousTests {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ChatMessageRepository chatMessageRepository;
    @Autowired
    private ChatMessageService chatMessageService;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private CommentService commentService;

    @Test
    public void wipeAndShowAllRecords() {
//        userRepository.deleteAll();
        chatMessageRepository.deleteAll();
//        postRepository.deleteAll();
//        commentRepository.deleteAll();
        System.out.println("Users: " + userRepository.findAll().size()
                + "\nChatMessages: " + chatMessageRepository.findAll().size()
                + "\nPosts: " + postRepository.findAll().size()
                + "\nComments: " + commentRepository.findAll().size());

    }

    @Test
    public void misc() {
        System.out.println(userRepository.findByUsername("dummy").getAge());
        System.out.println(userRepository.findByUsername("dummy").getFriends().size());
    }

    @Test
    public void deleteDataById() {
//        userRepository.deleteById(1L);
//        chatMessageRepository.deleteById(1237L);
        chatMessageRepository.deleteById(1238L);
//        postRepository.deleteById(991L);
//        commentRepository.deleteById(2205L);

//        userService.removeUser(1L);
//        chatMessageService.removeMessage(1237L);
        chatMessageService.removeMessage(1238L);
//        postService.removePost(991L);
//        commentService.removeComment(1L);
    }

    @Test
    public void showRecordsId() {
//        for (User user : userRepository.findAll()) { System.out.println(user.getUsername() + " : " + user.getId()); }
        for (ChatMessage chatMessage : chatMessageRepository.findAll()) { System.out.println(chatMessage.getContent() + " : " + chatMessage.getId()); }
//        for (Post post : postRepository.findAll()) { System.out.println(post.getContent() + " : " + post.getId()); }
//        for (Comment comment : commentRepository.findAll()) { System.out.println(comment.getContent() + " : " + comment.getId()); }
    }

    @Test
    public void insertUsers() {
//        User dummy = new User();
//        dummy.setUsername("dummy");
//        dummy.setPassword("drdr");
//        dummy.setMail("dreamydrummer@myplace.com");
//        dummy.setFirstName("Dreamy");
//        dummy.setLastName("Drummer");
//        dummy.setDateOfBirth(LocalDate.of(1992, 11, 23));
//
//        User silly = new User();
//        silly.setUsername("silly");
//        silly.setPassword("sisi");
//        silly.setMail("silkysillery@myplace.com");
//        silly.setFirstName("Silky");
//        silly.setLastName("Sillery");
//        silly.setDateOfBirth(LocalDate.of(1993, 4, 10));
//
//        User goofy = new User();
//        goofy.setUsername("goofy");
//        goofy.setPassword("glgl");
//        goofy.setMail("gleefulgluten@myplace.com");
//        goofy.setFirstName("Gleeful");
//        goofy.setLastName("Gluten");
//        goofy.setDateOfBirth(LocalDate.of(1989, 9, 1));
//
//        userRepository.save(dummy);
//        userRepository.save(silly);
//        userRepository.save(goofy);
    }
}
