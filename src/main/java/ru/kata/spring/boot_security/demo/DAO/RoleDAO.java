package ru.kata.spring.boot_security.demo.DAO;

import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.NoSuchElementException;

public interface RoleDAO {

    List<Role> findAll();

    Role findRoleByAuthority(String authority) throws NoSuchElementException;
}
