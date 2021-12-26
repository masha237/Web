package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Event;
import ru.itmo.wp.model.service.EventService;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class LogoutPage extends Page {
    private void action(HttpServletRequest request) {
        Event event = new Event();
        event.setType(Event.Type.LOGOUT);
        event.setUserId(getUser().getId());
        eventService.addEvent(event);
        removeUser();

        setMessage("Good bye. Hope to see you soon!");
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
