package ru.itmo.wp.web.page;

import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class LogoutPage extends Page {
    private void action(HttpServletRequest request) {
        removeUser();

        setMessage("Good bye. Hope to see you soon!");
        throw new RedirectException("/index");
    }
}
