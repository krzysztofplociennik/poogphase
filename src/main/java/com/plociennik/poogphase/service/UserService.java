package com.plociennik.poogphase.service;

import com.plociennik.poogphase.model.User;
import com.plociennik.poogphase.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(final User user) {
        return userRepository.save(user);
    }

    public Optional<User> getUser(final long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void removeUser(final long id) {
        userRepository.deleteById(id);
    }

    public User getUserByUsername(final String username) {
        return userRepository.findByUsername(username);
    }

    public void addFriend(User user, User friend) {
        user.getFriends().add(friend.getUsername());
        userRepository.save(user);
        friend.getFriends().add(user.getUsername());
        userRepository.save(friend);
    }

    public void deleteFriend(User user, User friend) {
        user.getFriends().remove(friend.getUsername());
        userRepository.save(user);
        friend.getFriends().remove(user.getUsername());
        userRepository.save(friend);
    }
}
