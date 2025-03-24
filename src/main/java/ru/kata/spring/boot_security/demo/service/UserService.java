package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    void updateUser(User user);

    void saveUser(User user);

    void deleteById(Long id);

    User getUser(Long id);

    User findUserByUsername(String username);

    UserDetails loadUserByUsername(String username);
}
