package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.User;
import com.plociennik.poogphase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAllUsers() {
        return repository.findAll();
    }

    public Optional<User> getUser(final long id) {
        return repository.findById(id);
    }

    public User saveUser(final User user) {
        return repository.save(user);
    }

    public void removeUser(final long id) {
        repository.deleteById(id);
    }

    public User getUserByUsername(String username) {
        return repository.findByUsername(username);
    }
}
