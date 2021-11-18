package ru.itmo.wp.web.page;


import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;

import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class EnterPage extends Page {
    private final UserService userService = new UserService();

    private void enter(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = userService.validateEnter(login, password);
        setUser(user);
        setMessage("Hello, " + user.getLogin());
        long id = user.getId();



        throw new RedirectException("/index");
    }
}
