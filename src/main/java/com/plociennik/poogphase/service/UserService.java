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
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(final long id) {
        return userRepository.findById(id);
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public void removeUser(final long id) {
        userRepository.deleteById(id);
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
