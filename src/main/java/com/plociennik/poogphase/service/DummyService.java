package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.DummyModel;
import com.plociennik.poogphase.repository.DummyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DummyService {

    @Autowired
    private DummyRepository repository;

    public List<DummyModel> getAllDummies() {
        return repository.findAll();
    }

    public DummyModel saveDummy(final DummyModel model) {
        return repository.save(model);
    }
}
