package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.User;
import com.plociennik.poogphase.model.dto.UserDto;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Stream;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestSuite {
    @Autowired
    private UserService userService;
    @Autowired
    private PostService postService;
    @Autowired
    private CommentService commentService;
    private long initialUserRepositorySize;

    @Before
    public void init() {
        initialUserRepositorySize = userService.getAllUsers().size();
    }

    @After
    public void finalCheck() {
        Assert.assertEquals(initialUserRepositorySize, userService.getAllUsers().size());
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setUsername("saveUsername");
        userService.saveUser(user);

        long searchedUserId = userService.getUserByUsername("saveUsername").getId();

        Assert.assertEquals( initialUserRepositorySize + 1, userService.getAllUsers().size());
        //Clean up
        userService.removeUser(searchedUserId);
    }

    @Test
    public void getAllUsers() {
        User user1 = new User();
        user1.setUsername("getAllUsername");
        User user2 = new User();
        user2.setUsername("getAllUsername2");

        userService.saveUser(user1);
        userService.saveUser(user2);

        long searchedUserId = userService.getUserByUsername("getAllUsername").getId();
        long searchedUserId2 = userService.getUserByUsername("getAllUsername2").getId();

        Assert.assertEquals(initialUserRepositorySize + 2, userService.getAllUsers().size());
        //Clean up
        userService.removeUser(searchedUserId);
        userService.removeUser(searchedUserId2);
    }

    @Test
    public void getUser() {
        User user = new User();
        user.setUsername("getUsername");
        userService.saveUser(user);

        long searchedUserId = userService.getUserByUsername("getUsername").getId();

        Assert.assertTrue(userService.getUser(searchedUserId).isPresent());
        //Clean up
        userService.removeUser(searchedUserId);
    }

    @Test
    public void editUser() {
        User user = new User();
        user.setUsername("editUsername");
        userService.saveUser(user);

        user.setMail("editMail");
        userService.saveUser(user);

        long searchedUserId = userService.getUserByUsername("editUsername").getId();

        Assert.assertEquals(initialUserRepositorySize + 1, userService.getAllUsers().size());
        Assert.assertEquals("editMail", userService.getUser(searchedUserId).get().getMail());
        //Clean up
        userService.removeUser(searchedUserId);
    }

    @Test
    public void deleteUser() {
        User user = new User();
        user.setUsername("deleteUsername");
        userService.saveUser(user);

        long searchedUserId = userService.getUserByUsername("deleteUsername").getId();

        userService.removeUser(searchedUserId);
    }

    @Test
    public void testIfCollectionsAreNotNullUponCreatingNewUser() {
        User user = new User();
        user.setUsername("notNullUsername");
        userService.saveUser(user);

        User searchedUser = userService.getUserByUsername("notNullUsername");

        Assert.assertNotEquals(null, searchedUser.getFriends());
        Assert.assertNotEquals(null, searchedUser.getMessages());
        Assert.assertNotEquals(null, searchedUser.getPosts());
        Assert.assertNotEquals(null, searchedUser.getComments());
        //Clean up
        userService.removeUser(searchedUser.getId());
    }

    @Test
    public void addAndDeleteFriend() {
        User dummy = userService.getUserByUsername("dummy");
        User silly = userService.getUserByUsername("silly");

        long sizeOfFriendsListInDummy = dummy.getFriends().size();
        long sizeOfFriendsListInSilly = silly.getFriends().size();

        userService.addFriend(dummy, silly);

        Assert.assertEquals(sizeOfFriendsListInDummy + 1, userService.getUserByUsername("dummy").getFriends().size());
        Assert.assertEquals(sizeOfFriendsListInSilly + 1, userService.getUserByUsername("silly").getFriends().size());

        userService.deleteFriend(dummy, silly);

        Assert.assertEquals(sizeOfFriendsListInDummy, userService.getUserByUsername("dummy").getFriends().size());
        Assert.assertEquals(sizeOfFriendsListInSilly, userService.getUserByUsername("silly").getFriends().size());
    }

    @Test
    public void editExistingUser() {
        User dummy = userService.getUserByUsername("dummy");
        dummy.setFirstName("Dreamy");
        userService.saveUser(dummy);

        Assert.assertEquals("Dreamy", userService.getUserByUsername("dummy").getFirstName());
    }

    @Test
    public void addFriendAndUpdateUser() {
        System.out.println("Dummy's friends before adding: ");
        for (String friend : userService.getUserByUsername("dummy").getFriends()) {
            System.out.println(friend);
        }
        User dummy = userService.getUserByUsername("dummy");
        long dummyFriendsSize = dummy.getFriends().size();
        User jackz = userService.getUserByUsername("jackz");
        long jackzFriendsSize = jackz.getFriends().size();

        userService.addFriend(dummy, jackz);

        Assert.assertEquals(dummyFriendsSize + 1, userService.getUserByUsername("dummy").getFriends().size());
        Assert.assertEquals(jackzFriendsSize + 1, userService.getUserByUsername("jackz").getFriends().size());
        System.out.println("Dummy's friends after adding: ");
        for (String friend : userService.getUserByUsername("dummy").getFriends()) {
            System.out.println(friend);
        }
    }

    @Test
    public void addingAlreadyFriend() {

    }

    @Test
    public void deletingFriendAndUpdateUser() {
        System.out.println("Dummy's friends before deleting: ");
        for (String friend : userService.getUserByUsername("dummy").getFriends()) {
            System.out.println(friend);
        }
        User dummy = userService.getUserByUsername("dummy");
        long dummyFriendsSize = dummy.getFriends().size();
        User jackz = userService.getUserByUsername("jackz");
        long jackzFriendsSize = jackz.getFriends().size();

        userService.deleteFriend(dummy, jackz);

        Assert.assertEquals(dummyFriendsSize - 1, userService.getUserByUsername("dummy").getFriends().size());
        Assert.assertEquals(jackzFriendsSize - 1, userService.getUserByUsername("jackz").getFriends().size());

        System.out.println("Dummy's friends after deleting: ");
        for (String friend : userService.getUserByUsername("dummy").getFriends()) {
            System.out.println(friend);
        }
    }

    @Test
    public void showUsersId() {
        for (User user : userService.getAllUsers()) {
            System.out.println(user.getUsername() + " " + user.getId());
        }
    }

    @Test
    public void showFriends() {
        for (String friend : userService.getUserByUsername("dummy").getFriends()) {
            System.out.println(friend);
        }
    }

    @Test
    public void showNumberOfRecords() {
        System.out.println("Number of records: " + userService.getAllUsers().size());
    }
}
