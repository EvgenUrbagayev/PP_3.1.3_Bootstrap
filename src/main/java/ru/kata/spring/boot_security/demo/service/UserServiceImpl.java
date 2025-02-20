package ru.kata.spring.boot_security.demo.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.RoleDAO;
import ru.kata.spring.boot_security.demo.DAO.UserDAO;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;


    @Autowired
    public UserServiceImpl(UserDAO userDAO, RoleDAO roleDAO) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }


    @Transactional
    @Override
    public List<User> findAll() {
        return userDAO.findAll();
    }

    @Transactional
    @Override
    public void saveUser(User user) {
        userDAO.saveUser(user);
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
        userDAO.deleteById(id);
    }

    @Transactional
    @Override
    public User getUser(Long id) {
        return userDAO.getUser(id);
    }

    @Transactional
    @Override
    public User findUserByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User '%s' not found", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), user.getRoles());
    }
}
