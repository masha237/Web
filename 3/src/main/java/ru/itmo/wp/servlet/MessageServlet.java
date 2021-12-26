package ru.itmo.wp.servlet;

import com.google.gson.Gson;
import ru.itmo.wp.util.Message;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MessageServlet extends HttpServlet {

    private final static String contentType = "application/json";
    private final List<Message> messages = new ArrayList<>();

    private void makeResponse(HttpServletResponse response, Object objectToConvert) throws IOException {
        String json = new Gson().toJson(objectToConvert);
        response.getWriter().print(json);
        response.getWriter().flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF8");
        response.setContentType(contentType);
        String uri = request.getRequestURI();
        switch (uri) {
            case "/message/auth":
                auth(request, response);
                break;
            case "/message/findAll":
                findAll(response, request);
                break;
            case "/message/add":
                add(request);
                break;
        }
    }

    private void add(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        String text = request.getParameter("text");
        if (!text.trim().isEmpty()) {
            messages.add(new Message(user, text));
        }
    }

    private void findAll(HttpServletResponse response, HttpServletRequest request) throws IOException {
        HttpSession session = request.getSession();
        String sessionUser = (String) session.getAttribute("user");
        if (sessionUser != null)
            makeResponse(response, messages);
    }

    private void auth(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String sessionUser = (String) session.getAttribute("user");
        String requestUser = request.getParameter("user");
        String user = "";
        if (requestUser != null && !requestUser.trim().isEmpty()) {
            user = requestUser;
        } else if (sessionUser != null) {
            user = sessionUser;
        }
        if (!user.isEmpty()) {
            session.setAttribute("user", user);
        }
        makeResponse(response, user);
    }
}
