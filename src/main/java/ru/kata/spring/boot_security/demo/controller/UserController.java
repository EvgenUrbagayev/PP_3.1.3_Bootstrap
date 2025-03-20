package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    @GetMapping("/user")
    public String showUserInfo(Model model) {
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }

    @GetMapping("/admin")
    public String findAll(Model model, Principal principal) {
        model.addAttribute("user", userService.findUserByUsername(principal.getName()));
        List<User> users = userService.findAll();
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("users", users);
        return "admin-list";
    }

    @GetMapping("/admin-create")
    public String createUserForm(User user) {
        return "admin-create";
    }

    @PostMapping("/admin-create")
    public String createUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin-list";
    }

    @GetMapping("/admin-delete")
    public String deleteUser(@RequestParam("userId") Long id) {
        userService.deleteById(id);
        return "redirect:/admin-list";
    }

    @GetMapping("/admin-update")
    public String updateUserForm(@RequestParam("userId") Long id, Model model) {
        User user = userService.getUser(id);
        model.addAttribute("user", user);
        return "admin-update";
    }

    @PostMapping("/admin-update")
    public String updateUser(User user) {
        userService.saveUser(user);
        return "redirect:/admin-list";
    }
}
