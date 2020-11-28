package com.plociennik.poogphase.repository;

import com.plociennik.poogphase.model.DummyModel;
import com.plociennik.poogphase.service.DummyService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DummyRepositoryTestSuite {

    @Autowired
    private DummyRepository repository;
    @Autowired
    private DummyService service;

    @Test
    public void saveModel() {

        long sizeBeforeSaving = repository.count();
        service.saveDummy(new DummyModel(1L, "name", 25));

        System.out.println("Size 1: " + sizeBeforeSaving + "\n" + "Size 2: " + repository.count());


//        long id = repository.findByName("name2").getId();
//        repository.deleteById(id);
        System.out.println("Size after deleting: " + repository.count());

    }

    @Test
    public void howManyRecords() {
        System.out.println("This is how many records we have: " + repository.count());
    }

    @Test
    public void isDatabaseEmpty() {
        Assert.assertEquals(0, repository.findAll().size());
    }
}
