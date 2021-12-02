package ru.itmo.wp.lesson8.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.lesson8.service.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class UsersPage extends Page {
    private final UserService userService;

    public UsersPage(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/all")
    public String users(Model model, HttpSession httpSession) {
        model.addAttribute("users", userService.findAll());
        model.addAttribute("loggedUser", getUser(httpSession));
        model.addAttribute("users", userService.findAll());
        model.addAttribute("newValue", null);
        model.addAttribute("userId", null);
        return "UsersPage";
    }

    @PostMapping("/users/all")
    public String changeDisabled(@ModelAttribute("userId") String userId, @ModelAttribute("newValue") String newValue) {
        try {
            long id = Long.parseLong(userId);
            boolean value = Boolean.parseBoolean(newValue);
            userService.setDisabled(id, value);
        } catch (NumberFormatException ignored) {
            // No operations.
        }
        return "redirect:/users/all";
    }
}
