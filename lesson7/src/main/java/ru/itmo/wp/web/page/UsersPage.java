package ru.itmo.wp.web.page;


import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class UsersPage extends Page {
    private void findAll(HttpServletRequest request, Map<String, Object> view) {
        before(request, view);
        view.put("users", userService.findAll());
    }

    private void changeAdminProperty(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        User user = getUser();
        long id = Long.parseLong(request.getParameter("userToChange"));
        boolean newValue = Boolean.parseBoolean(request.getParameter("newValue"));
        User userToChange = userService.find(id);
        userService.validateChangeUser(user, userToChange);
        if (userToChange.isAdmin() != newValue) {
            userService.setAdminProp(id, newValue);
        }
        view.put("currentAdminProp", newValue);
    }
}
