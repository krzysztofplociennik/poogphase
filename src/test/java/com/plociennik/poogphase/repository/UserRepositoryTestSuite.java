package com.plociennik.poogphase.repository;

import com.plociennik.poogphase.model.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTestSuite {
    @Autowired
    private UserRepository userRepository;
    private long initialUserRepositorySize;

    @Before
    public void init() {
        initialUserRepositorySize = userRepository.count();
    }

    @After
    public void finalCheck() {
        Assert.assertEquals(initialUserRepositorySize, userRepository.count());
    }

    @Test
    public void saveUser() {
        User user = new User();
        user.setUsername("getUsername");
        userRepository.save(user);

        long searchedUserId = userRepository.findByUsername("getUsername").getId();
        //Then
        Assert.assertEquals(initialUserRepositorySize + 1, userRepository.count());
        //Clean up
        userRepository.deleteById(searchedUserId);
    }

    @Test
    public void getAllUsers() {
        User user1 = new User();
        user1.setUsername("getAllUsername");
        User user2 = new User();
        user2.setUsername("getAllUsername2");
        userRepository.saveAll(Arrays.asList(user1, user2));

        long searchedUserId = userRepository.findByUsername("getAllUsername").getId();
        long searchedUserId2 = userRepository.findByUsername("getAllUsername2").getId();

        Assert.assertEquals(initialUserRepositorySize + 2, userRepository.count());
        //Clean up
        userRepository.deleteById(searchedUserId);
        userRepository.deleteById(searchedUserId2);
    }

    @Test
    public void getUser() {
        User user = new User();
        user.setUsername("getUsername");
        userRepository.save(user);

        long searchedUserId = userRepository.findByUsername("getUsername").getId();

        Assert.assertTrue(userRepository.findById(searchedUserId).isPresent());
        //Clean up
        userRepository.deleteById(searchedUserId);
    }

    @Test
    public void editUser() {
        User user = new User();
        user.setUsername("editUsername");
        userRepository.save(user);

        user.setMail("editMail");
        userRepository.save(user);

        long searchedUserId = userRepository.findByUsername("editUsername").getId();

        Assert.assertEquals(initialUserRepositorySize + 1, userRepository.count());
        Assert.assertEquals("editMail", userRepository.findById(searchedUserId).get().getMail());
        //Clean up
        userRepository.deleteById(searchedUserId);
    }

    @Test
    public void deleteUser() {
        //Given
        User user1 = new User();
        user1.setUsername("deleteUsername");
        userRepository.save(user1);

        long searchedUserId = userRepository.findByUsername("deleteUsername").getId();

        userRepository.deleteById(searchedUserId);
    }

    @Test
    public void testIfCollectionsAreNotNullUponCreatingNewUser() {
        //Given
        User user = new User();
        user.setUsername("notNullUsername");
        userRepository.save(user);

        User searchedUser = userRepository.findByUsername("notNullUsername");

        Assert.assertNotEquals(null, searchedUser.getFriends());
        Assert.assertNotEquals(null, searchedUser.getChatArchive());
        Assert.assertNotEquals(null, searchedUser.getPosts());
        Assert.assertNotEquals(null, searchedUser.getComments());
        //Clean up
        userRepository.deleteById(searchedUser.getId());
    }

    @Test
    public void showNumberOfRecords() {
        System.out.println("Number of records: " + userRepository.count());
    }
}
