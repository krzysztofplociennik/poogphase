package com.plociennik.poogphase.repository;

import com.plociennik.poogphase.model.DummyModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DummyRepository extends JpaRepository<DummyModel, Long> {

    DummyModel findByName(String name);

}
