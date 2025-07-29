package com.us.urlshortener.controller;

import com.us.urlshortener.model.User;
import com.us.urlshortener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signup(User user) {
        userService.register(user);
        return "redirect:/html/login.html"; // after signup
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        boolean success = userService.login(username, password);
        return success ? "redirect:/html/dashboard.html" : "redirect:/html/login.html?error=true";
    }
}
