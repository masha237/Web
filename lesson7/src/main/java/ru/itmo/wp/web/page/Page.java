package ru.itmo.wp.web.page;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.service.ArticleService;
import ru.itmo.wp.model.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public abstract class Page {

    final ArticleService articleService = new ArticleService();
    final UserService userService = new UserService();
    private HttpServletRequest mainRequest;

    protected void before(HttpServletRequest request, Map<String, Object> view) {
        mainRequest = request;
        putUser(request, view);
    }

    private void putUser(HttpServletRequest request, Map<String, Object> view) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            view.put("user", user);
        }
    }

    protected void putMessage(HttpServletRequest request, Map<String, Object> view) {
        String message = (String) request.getSession().getAttribute("message");
        if (!Strings.isNullOrEmpty(message)) {
            view.put("message", message);
            request.getSession().removeAttribute("message");
        }
    }

    protected void setMessage(String messages) {
        if (messages != null) {
            mainRequest.getSession().setAttribute("message", messages);
        }
    }

    User getUser() {
        return (User) mainRequest.getSession().getAttribute("user");
    }

    void setUser(User user) {
        mainRequest.getSession().setAttribute("user", user);
    }

    void removeUser() {
        mainRequest.getSession().removeAttribute("user");
    }

    protected void action() {
        // No operations.
    }
}
