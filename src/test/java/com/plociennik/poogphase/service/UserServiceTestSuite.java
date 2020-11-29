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
    private UserService service;

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
            service.saveUser(user);
        }
    }

    @After
    public void cleanUpData() {
        for (String username : Arrays.asList("marqez", "atomix", "wiki", "paul")) {
            service.removeUser(service.getUserByUsername(username).getId());
        }
    }

    @Test
    public void saveUser() {
        //Given
        long sizeBeforeSaving = service.getAllUsers().size();
        User dummyUser = new User();
        dummyUser.setUsername("dummy");
        //When
        service.saveUser(dummyUser);
        long dummyId = service.getUserByUsername("dummy").getId();
        //Then
        Assert.assertEquals(sizeBeforeSaving + 1, service.getAllUsers().size());
        //Clean up
        service.removeUser(dummyId);
    }

    @Test
    public void getAllUsers() {
        //Given
        long sizeBeforeSaving = service.getAllUsers().size();
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");
        //When
        service.saveUser(user1); service.saveUser(user2);
        long dummyId1 = service.getUserByUsername("user1").getId();
        long dummyId2 = service.getUserByUsername("user2").getId();
        //Then
        Assert.assertEquals(sizeBeforeSaving + 2, service.getAllUsers().size());
        //Clean up
        service.removeUser(dummyId1);
        service.removeUser(dummyId2);
    }

    @Test
    public void getUser() {
        //Given

        //When
        long searchedUserId = service.getUserByUsername("marqez").getId();
        //Then
        Assert.assertEquals("marqez@gmail.com", service.getUser(searchedUserId).get().getMail());
        //Clean up
    }

    @Test
    public void editUser() {
        //Given
        long sizeBeforeEditing = service.getAllUsers().size();
        //When
        User searchedUser = service.getUserByUsername("atomix");
        searchedUser.setMail("atomix2@gmail.com");
        service.saveUser(searchedUser);
        //Then
        Assert.assertEquals(sizeBeforeEditing, service.getAllUsers().size());
        Assert.assertEquals("atomix2@gmail.com", service.getUserByUsername("atomix").getMail());
        //Clean up
    }

    @Test
    public void deleteUser() {
        //Given
        long sizeBeforeDeleting = service.getAllUsers().size();
        User user1 = new User();
        user1.setUsername("dummy");
        service.saveUser(user1);
        //When
        long dummyId = service.getUserByUsername("dummy").getId();
        service.removeUser(dummyId);
        //Then
        Assert.assertFalse(service.getAllUsers().contains(user1));
        Assert.assertEquals(sizeBeforeDeleting, service.getAllUsers().size());
    }

    @Test
    public void testIfCollectionsAreNotNullUponCreatingNewUser() {
        //Given
        User searchedUser = service.getUserByUsername("paul");
        //When
        //Then
        Assert.assertNotEquals(null, searchedUser.getFriends());
        Assert.assertNotEquals(null, searchedUser.getChatArchive());
        Assert.assertNotEquals(null, searchedUser.getPosts());
        Assert.assertNotEquals(null, searchedUser.getComments());
        //Clean up
    }
}
