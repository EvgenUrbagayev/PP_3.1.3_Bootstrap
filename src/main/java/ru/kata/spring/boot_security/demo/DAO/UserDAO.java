package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDAO {

    List<User> findAll();

    void saveUser(User user);

    void deleteById(Long id);

    User getUser(Long id);

    User findUserByUsername(String username);

}
