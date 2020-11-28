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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTestSuite {

    @Autowired
    private UserRepository repository;

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
        repository.saveAll(Arrays.asList(mark, peter, victoria, paula));
    }

    @After
    public void cleanUpData() {
        repository.deleteById(repository.findByUsername("marqez").getId());
        repository.deleteById(repository.findByUsername("atomix").getId());
        repository.deleteById(repository.findByUsername("wiki").getId());
        repository.deleteById(repository.findByUsername("paul").getId());
    }

    @Test
    public void saveUser() {
        //Given
        long sizeBeforeSaving = repository.count();
        User dummyUser = new User();
        dummyUser.setUsername("dummy");
        //When
        repository.save(dummyUser);
        long dummyId = repository.findByUsername("dummy").getId();
        //Then
        Assert.assertEquals(sizeBeforeSaving + 1, repository.count());
        //Clean up
        repository.deleteById(dummyId);
    }

    @Test
    public void getAllUsers() {
        //Given
        long sizeBeforeSaving = repository.count();
        User user1 = new User();
        user1.setUsername("user1");
        User user2 = new User();
        user2.setUsername("user2");
        //When
        repository.saveAll(Arrays.asList(user1, user2));
        long dummyId1 = repository.findByUsername("user1").getId();
        long dummyId2 = repository.findByUsername("user2").getId();
        //Then
        Assert.assertEquals(sizeBeforeSaving + 2, repository.count());
        //Clean up
        repository.deleteById(dummyId1);
        repository.deleteById(dummyId2);
    }

    @Test
    public void getUser() {
        //Given

        //When
        User searchedUser = repository.findByUsername("marqez");
        //Then
        Assert.assertEquals("marqez@gmail.com", searchedUser.getMail());
        //Clean up
    }

    @Test
    public void editUser() {
        //Given
        long sizeBeforeEditing = repository.count();
        //When
        User searchedUser = repository.findByUsername("atomix");
        searchedUser.setMail("atomix2@gmail.com");
        repository.save(searchedUser);
        //Then
        Assert.assertEquals(sizeBeforeEditing, repository.count());
        Assert.assertEquals("atomix2@gmail.com", repository.findByUsername("atomix").getMail());
        //Clean up
    }

    @Test
    public void deleteUser() {
        //Given
        long sizeBeforeDeleting = repository.count();
        User user1 = new User();
        user1.setUsername("dummy");
        repository.save(user1);
        //When
        long dummyId = repository.findByUsername("dummy").getId();
        repository.deleteById(dummyId);
        //Then
        Assert.assertFalse(repository.findAll().contains(user1));
        Assert.assertEquals(sizeBeforeDeleting, repository.count());
    }

    @Test
    public void testIfCollectionsAreNotNullUponCreatingNewUser() {
        //Given
        User searchedUser = repository.findByUsername("paul");
        //When
        //Then
        Assert.assertNotEquals(null, searchedUser.getFriends());
        Assert.assertNotEquals(null, searchedUser.getChatArchive());
        Assert.assertNotEquals(null, searchedUser.getPosts());
        Assert.assertNotEquals(null, searchedUser.getComments());
        //Clean up
    }
}
