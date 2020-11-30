package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestSuite {

    @Autowired
    private UserService userService;

    @Before
    public void initSomeData() {
        User mark = new User(1L, "marqez", "marq", "marqez@gmail.com", "Mark", "Jey",
                LocalDate.of(1991, 6, 12), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>());
        User peter = new User(2L, "atomix", "124", "atomix@gmail.com", "Peter", "Kowalski",
                LocalDate.of(1995, 1, 28), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>());
        User victoria = new User(3L, "wiki", "wix", "wiki@gmail.com", "Victoria", "Fran",
                LocalDate.of(1993, 10, 2), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new HashMap<>());
        User paula = new User();
        paula.setUsername("paul");
        for (User user  : Arrays.asList(mark, peter, victoria, paula)) {
            userService.saveUser(user);
        }
    }

    @After
    public void cleanUpData() {
        for (String username : Arrays.asList("marqez", "atomix", "wiki", "paul")) {
            userService.removeUser(userService.getUserByUsername(username).getId());
        }
    }

    @Test
    public void saveUser() {
        //Given
        long sizeBeforeSaving = userService.getAllUsers().size();
        User dummyUser = new User();
        dummyUser.setUsername("dummy");
        //When
        userService.saveUser(dummyUser);
        long dummyId = userService.getUserByUsername("dummy").getId();
        //Then
        Assert.assertEquals(sizeBeforeSaving + 1, userService.getAllUsers().size());
        //Clean up
        userService.removeUser(dummyId);
    }

    @Test
    public void getAllUsers() {
        //Given
        long sizeBeforeSaving = userService.getAllUsers().size();
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");
        //When
        userService.saveUser(user1); userService.saveUser(user2);
        long dummyId1 = userService.getUserByUsername("user1").getId();
        long dummyId2 = userService.getUserByUsername("user2").getId();
        //Then
        Assert.assertEquals(sizeBeforeSaving + 2, userService.getAllUsers().size());
        //Clean up
        userService.removeUser(dummyId1);
        userService.removeUser(dummyId2);
    }

    @Test
    public void getUser() {
        //Given

        //When
        long searchedUserId = userService.getUserByUsername("marqez").getId();
        //Then
        Assert.assertTrue(userService.getUser(searchedUserId).isPresent());
        //Clean up
    }

    @Test
    public void editUser() {
        //Given
        long sizeBeforeEditing = userService.getAllUsers().size();
        //When
        User searchedUser = userService.getUserByUsername("atomix");
        searchedUser.setMail("atomix2@gmail.com");
        userService.saveUser(searchedUser);
        //Then
        Assert.assertEquals(sizeBeforeEditing, userService.getAllUsers().size());
        Assert.assertEquals("atomix2@gmail.com", userService.getUserByUsername("atomix").getMail());
        //Clean up
    }

    @Test
    public void deleteUser() {
        //Given
        long sizeBeforeDeleting = userService.getAllUsers().size();
        User user1 = new User();
        user1.setUsername("dummy");
        userService.saveUser(user1);
        //When
        long dummyId = userService.getUserByUsername("dummy").getId();
        userService.removeUser(dummyId);
        //Then
        Assert.assertFalse(userService.getAllUsers().contains(user1));
        Assert.assertEquals(sizeBeforeDeleting, userService.getAllUsers().size());
    }

    @Test
    public void testIfCollectionsAreNotNullUponCreatingNewUser() {
        //Given
        User searchedUser = userService.getUserByUsername("paul");
        //When
        //Then
        Assert.assertNotEquals(null, searchedUser.getFriends());
        Assert.assertNotEquals(null, searchedUser.getChatArchive());
        Assert.assertNotEquals(null, searchedUser.getPosts());
        Assert.assertNotEquals(null, searchedUser.getComments());
        //Clean up
    }
}
