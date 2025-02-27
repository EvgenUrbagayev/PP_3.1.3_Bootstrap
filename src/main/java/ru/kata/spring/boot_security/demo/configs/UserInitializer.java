package ru.kata.spring.boot_security.demo.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.DAO.RoleDAO;
import ru.kata.spring.boot_security.demo.DAO.UserDAO;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.Collections;

@Component
@Transactional
public class UserInitializer implements CommandLineRunner {

    private final UserDAO userDAO;
    private final RoleDAO roleDAO;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserInitializer(UserDAO userDAO, RoleDAO roleDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        roleDAO.save(adminRole);

        Role userRole = new Role();
        userRole.setName("ROLE_USER");
        roleDAO.save(userRole);


        User user = new User();
        user.setName("User");
        user.setSurname("Userov");
        user.setAge(30);
        user.setPassword(passwordEncoder.encode("user"));
        user.setRoles(Collections.singleton(userRole));
        userDAO.saveUser(user);


        User admin = new User();
        admin.setName("admin");
        admin.setSurname("Adminov");
        admin.setAge(40);
        admin.setPassword(passwordEncoder.encode("admin"));
        admin.setRoles(Collections.singleton(adminRole));
        userDAO.saveUser(admin);

    }
}
