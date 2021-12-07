package ru.itmo.wp.lesson8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itmo.wp.lesson8.service.UserService;

@Controller
public class UserPage extends Page {
    private final UserService userService;

    public UserPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({ "/user/{id}"})
    public String userView(@PathVariable String id, Model model) {
        try {
            model.addAttribute("userView", userService.findById(Long.parseLong(id)));
        } catch (Exception ignore) {}
        return "UserPage";
    }
}
