package com.plociennik.poogphase.repository;

import com.plociennik.poogphase.model.DummyModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DummyRepository extends JpaRepository<DummyModel, Long> {

    @Override
    List<DummyModel> findAll();

    DummyModel findByName(String name);

}
