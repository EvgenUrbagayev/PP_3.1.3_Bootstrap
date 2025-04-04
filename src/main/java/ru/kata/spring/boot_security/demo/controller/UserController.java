
package ru.kata.spring.boot_security.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    public String showUserInfo(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        return "user";
    }

    @GetMapping("/admin")
    public String findAll(Model model, Principal principal) {
        model.addAttribute("user", userService.findByUsername(principal.getName()));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("roles", roleService.findAll());
        model.addAttribute("newUser", new User());
        return "admin-list";
    }

    @PostMapping("admin/saveUser")
    public String addUser(@ModelAttribute("newUser") User user,
                          @RequestParam("roles") List<Long> roleIds) {
        Set<Role> roles = roleIds.stream()
                .map(roleService::findById)
                .collect(Collectors.toSet());
        user.setRoles(roles);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @PostMapping("admin/updateUser")
    public String updateUser(@ModelAttribute User user,
                             @RequestParam("roles") List<Long> roleIds) {
        user.setRoles(roleService.findRolesByIds(roleIds));
        userService.updateUser(user);
        return "redirect:/admin";
    }

    @PostMapping("admin/deleteUser")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
