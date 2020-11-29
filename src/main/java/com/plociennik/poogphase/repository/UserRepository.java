package com.plociennik.poogphase.repository;

import com.plociennik.poogphase.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Override
    List<User> findAll();

    User findByUsername(String username);

    User findByUsernameAndPassword(String username, String password);
}
