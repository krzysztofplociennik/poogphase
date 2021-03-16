package com.plociennik.poogphase.repository.other;

import com.plociennik.poogphase.mapper.UserMapper;
import com.plociennik.poogphase.model.User;
import com.plociennik.poogphase.model.dto.UserDto;
import com.plociennik.poogphase.repository.*;
import com.plociennik.poogphase.service.ChatMessageService;
import com.plociennik.poogphase.service.CommentService;
import com.plociennik.poogphase.service.PostService;
import com.plociennik.poogphase.service.UserService;
import com.plociennik.poogphase.view.client.ApiClient;
import com.plociennik.poogphase.view.logic.FriendsManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.*;

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
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ApiClient apiClient;

    @Test
    public void wipeAndShowAllRecords() {
//        userRepository.deleteAll();
//        chatMessageRepository.deleteAll();
//        postRepository.deleteAll();
//        commentRepository.deleteAll();
        System.out.println("Users: " + userRepository.findAll().size()
                + "\nChatMessages: " + chatMessageRepository.findAll().size()
                + "\nPosts: " + postRepository.findAll().size()
                + "\nComments: " + commentRepository.findAll().size());
    }

    @Test
    public void deleteDataById() {
//        userRepository.deleteById(1L);
//        chatMessageRepository.deleteById(4L);
//        chatMessageRepository.deleteById(1238L);
//        postRepository.deleteById(991L);
//        commentRepository.deleteById(2205L);

//        userService.removeUser(683L);
//        userService.removeUser(676L);
//        chatMessageService.removeMessage(4L);
//        chatMessageService.removeMessage(1238L);
//        postService.removePost(991L);
//        commentService.removeComment(1L);
    }

    @Test
    public void showRecordsId() {
        for (User user : userRepository.findAll()) { System.out.println(user.getUsername() + " : " + user.getId()); }
//        for (ChatMessage chatMessage : chatMessageRepository.findAll()) { System.out.println(chatMessage.getContent() + " : " + chatMessage.getId()); }
//        for (Post post : postRepository.findAll()) { System.out.println(post.getContent() + " : " + post.getId()); }
//        for (Comment comment : commentRepository.findAll()) { System.out.println(comment.getContent() + " : " + comment.getId()); }
    }

    @Test
    public void insertData() {
        insertUsers();
        insertPosts();
        insertComments();
        insertMessages();
    }

    @Test
    public void insertUsers() {
        try {
            userService.getUser(userService.getUserByUsername("dummy").getId()).isEmpty();
        } catch (NullPointerException e) {
            User dummy = new User();
            dummy.setUsername("dummy");
            dummy.setPassword("drdr");
            dummy.setMail("dreamydrummer@myplace.com");
            dummy.setFirstName("Dreamy");
            dummy.setLastName("Drummer");
            dummy.setDateOfBirth(LocalDate.of(1992, 11, 23));
            Set<String> friends = new LinkedHashSet<>();
            friends.add("silly"); friends.add("molina");
            dummy.setFriends(friends);
            userRepository.save(dummy);
        }
        try {
            userService.getUser(userService.getUserByUsername("silly").getId()).isEmpty();
        } catch (NullPointerException e) {
            User silly = new User();
            silly.setUsername("silly");
            silly.setPassword("sisi");
            silly.setMail("silkysillery@myplace.com");
            silly.setFirstName("Silky");
            silly.setLastName("Sillery");
            silly.setDateOfBirth(LocalDate.of(1993, 4, 10));
            Set<String> friends = new LinkedHashSet<>();
            friends.add("dummy"); friends.add("goofy"); friends.add("jackz"); friends.add("molina");
            silly.setFriends(friends);
            userRepository.save(silly);
        }
        try {
            userService.getUser(userService.getUserByUsername("goofy").getId()).isEmpty();
        } catch (NullPointerException e) {
            User goofy = new User();
            goofy.setUsername("goofy");
            goofy.setPassword("glgl");
            goofy.setMail("gleefulgluten@myplace.com");
            goofy.setFirstName("Gleeful");
            goofy.setLastName("Gluten");
            goofy.setDateOfBirth(LocalDate.of(1989, 9, 1));
            Set<String> friends = new LinkedHashSet<>();
            friends.add("silly"); friends.add("molina"); friends.add("glory");
            goofy.setFriends(friends);
            userRepository.save(goofy);
        }
        try {
            userService.getUser(userService.getUserByUsername("jackz").getId()).isEmpty();
        } catch (NullPointerException e) {
            User jackz = new User();
            jackz.setUsername("jackz");
            jackz.setPassword("jkjk");
            jackz.setMail("jackful@myplace.com");
            jackz.setFirstName("Jack");
            jackz.setLastName("Fuller");
            jackz.setDateOfBirth(LocalDate.of(1990, 5, 12));
            Set<String> friends = new LinkedHashSet<>();
            friends.add("silly");
            jackz.setFriends(friends);
            userRepository.save(jackz);
        }
        try {
            userService.getUser(userService.getUserByUsername("molina").getId()).isEmpty();
        } catch (NullPointerException e) {
            User molina = new User();
            molina.setUsername("molina");
            molina.setPassword("mlml");
            molina.setMail("molinagala@myplace.com");
            molina.setFirstName("Melanie");
            molina.setLastName("Gallow");
            molina.setDateOfBirth(LocalDate.of(1992, 2, 28));
            Set<String> friends = new LinkedHashSet<>();
            friends.add("dummy"); friends.add("silly"); friends.add("goofy"); friends.add("glory");
            molina.setFriends(friends);
            userRepository.save(molina);
        }
        try {
            userService.getUser(userService.getUserByUsername("glory").getId()).isEmpty();
        } catch (NullPointerException e) {
            User glory = new User();
            glory.setUsername("glory");
            glory.setPassword("glgl");
            glory.setMail("glorywhole@myplace.com");
            glory.setFirstName("Gloria");
            glory.setLastName("Winnston");
            glory.setDateOfBirth(LocalDate.of(1988, 10, 18));
            Set<String> friends = new LinkedHashSet<>();
            friends.add("goofy"); friends.add("molina");
            glory.setFriends(friends);
            userRepository.save(glory);
        }
        System.out.println("User size: " + userService.getAllUsers().size());
    }

    @Test
    public void insertPosts() {
        if (userService.getAllUsers().size() != 0) {

        }
    }

    @Test
    public void insertComments() {
        if (userService.getAllUsers().size() != 0 && postService.getAllPosts().size() != 0) {

        }
    }

    @Test
    public void insertMessages() {
        if (userService.getAllUsers().size() != 0) {

        }
    }

    @Test
    public void addFriends() {
        User dummy = userService.getUserByUsername("dummy");
        Set<String> friends = dummy.getFriends();
        friends.add("silly"); friends.add("molina");
        dummy.setFriends(friends);
//        userService.saveUser(dummy);

        User silly = userService.getUserByUsername("silly");
        Set<String> sillyFriends = silly.getFriends();
        sillyFriends.add("dummy"); sillyFriends.add("goofy"); sillyFriends.add("jackz"); sillyFriends.add("molina");
        silly.setFriends(sillyFriends);
//        userService.saveUser(silly);

        User goofy = userService.getUserByUsername("goofy");
        Set<String> goofyFriends = goofy.getFriends();
        goofyFriends.add("silly"); goofyFriends.add("molina"); goofyFriends.add("glory");
        goofy.setFriends(goofyFriends);
//        userService.saveUser(goofy);
    }

    @Test
    public void checkIfFriends() {
        FriendsManager friendsManager = new FriendsManager(this.apiClient);

        UserDto dummy = userMapper.mapToUserDto(userService.getUserByUsername("dummy"));
        UserDto silly = userMapper.mapToUserDto(userService.getUserByUsername("silly"));
        UserDto goofy = userMapper.mapToUserDto(userService.getUserByUsername("goofy"));
        UserDto glory = userMapper.mapToUserDto(userService.getUserByUsername("glory"));

        System.out.println(friendsManager.areTheyFriends(silly, silly));
        System.out.println(friendsManager.areTheyFriends(goofy, dummy));
        System.out.println(friendsManager.areTheyFriends(dummy, glory));
    }

    @Test
    public void checkListOfFriends() {
        User user = userService.getUserByUsername("dummy");

        System.out.println("Current friends: ");
        for (String friend : userService.getUserByUsername("dummy").getFriends()) {
            System.out.println(friend);
        }

        user.getFriends().add("testingFriend");
        userService.saveUser(user);

        System.out.println("After adding: ");
        for (String friend : userService.getUserByUsername("dummy").getFriends()) {
            System.out.println(friend);
        }

        user.getFriends().remove("testingFriend");
        String friendToRemove = userService.getUserByUsername("dummy").getFriends().stream().filter(s -> s.equals("testingFriend")).findAny().get();
        userService.getUserByUsername("dummy").getFriends().remove(friendToRemove);
        userService.saveUser(user);

        System.out.println("After removing: ");
        for (String friend : userService.getUserByUsername("dummy").getFriends()) {
            System.out.println(friend);
        }
    }

    @Test
    public void misc() {
        System.out.println(userRepository.findByUsername("dummy").getAge());
        System.out.println(userRepository.findByUsername("dummy").getFriends().size());
    }
}
