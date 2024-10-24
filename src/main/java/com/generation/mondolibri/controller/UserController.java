package com.generation.mondolibri.controller;

import com.generation.mondolibri.exceptions.checked.AuthenticationException;
import com.generation.mondolibri.exceptions.checked.RoleNotFoundException;
import com.generation.mondolibri.exceptions.checked.UserAlreadyExistsException;
import com.generation.mondolibri.exceptions.checked.UserNotFoundException;
import com.generation.mondolibri.model.entity.Role;
import com.generation.mondolibri.model.entity.User;
import com.generation.mondolibri.service.implementation.RoleService;
import com.generation.mondolibri.service.implementation.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping("/login")  // Changed to POST since you're handling login credentials
    public String login(HttpSession session,
                        @RequestParam(value = "login_username") String username,
                        @RequestParam(value = "login_password") String password)
            throws NoSuchAlgorithmException, UserNotFoundException, AuthenticationException {
            User loggedUser = userService.loginWithUser(username, password);
        initializeSession(session, loggedUser);
        return "index";
    }

    @PostMapping("/register")
    public String register(HttpSession session,
                           @RequestParam("registration_username") String username,
                           @RequestParam("registration_password") String password,
                           @RequestParam("registration_email") String email,
                           @RequestParam("registration_address") String address)
            throws RoleNotFoundException, NoSuchAlgorithmException, UserAlreadyExistsException {
        Role role = roleService.getUSer();
        User user = User.createInstance(role, username, password, email, address);
        User registeredUser = userService.register(user);
        initializeSession(session, registeredUser);
        return "index";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "index";
    }

    @GetMapping("/admin/all")
    public String allUsers(Model model) {
        List<User> user = userService.findAll();
        model.addAttribute("user", user);
        return "privata/admin/allusers";
    }

    private static void initializeSession(HttpSession session, User user) {
        session.setAttribute("username", user.getUsername());
        session.setAttribute("role", user.getRole().getNome());
    }
}
