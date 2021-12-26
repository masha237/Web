package ru.itmo.wp.web.page;


import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.service.UserService;
import ru.itmo.wp.model.service.EventService;

import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class EnterPage extends Page {
    private final UserService userService = new UserService();

    private void enter(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        String loginOrEmail = request.getParameter("loginOrEmail");
        String password = request.getParameter("password");

        User user = userService.validateEnter(loginOrEmail, password);
        setUser(user);
        setMessage("Hello, " + user.getLogin());
        long id = user.getId();

        Event event = new Event();
        event.setType(Event.Type.ENTER);
        event.setUserId(getUser().getId());
        eventService.addEvent(event);


        throw new RedirectException("/index");
    }

    @Override
    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
    }

    @Override
    protected void after(HttpServletRequest request, Map<String, Object> view) {
        super.after(request, view);
    }
}
