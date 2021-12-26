package ru.itmo.wp.servlet;

import ru.itmo.wp.util.ImageUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Base64;
import java.util.Objects;
import java.util.Random;

public class CaptchaFilter extends HttpFilter {

    private static final Random random = new Random();

    private String getCaptchaForm(String code) {
        String encodedImage = Base64.getEncoder().encodeToString(ImageUtils.toPng(code));
        return "<!DOCTYPE html>\n" +
                "<html lang=\"en\">" +
                "<head>" +
                "<meta charset=\"UTF-8\">" +
                "</head>" +
                "<body style=\"text-align: center;\">" +
                "<img src=\"data:image/png;base64, "
                + encodedImage +
                "\"/>" +
                "<form method=\"post\" action=\"\">" +
                "<label for=\"User-Captcha\">Enter Captcha: </label>" +
                "<input name=\"User-Captcha\" id=\"User-Captcha\"/>" +
                "</form>" +
                "</body>" +
                "</html>";
    }

    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        if (request.getRequestURI().endsWith("/captcha")) {
            if ("true".equals(session.getAttribute("passedCaptcha"))) {
                Object origURI = session.getAttribute("origURI");
                if (origURI == null || origURI == "/captcha") {
                    origURI = "/index";
                }
                response.sendRedirect((String) origURI);
            }
            if ("GET".equals(request.getMethod())) {
                sendCaptcha(request, response, chain);
            } else if ("POST".equals(request.getMethod())) {
                checkCaptcha(request, response, chain);
            } else {
                response.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
            }
        } else if ("GET".equals(request.getMethod())) {
            if (!"true".equals(session.getAttribute("passedCaptcha"))) {
                session.setAttribute("origURI", request.getRequestURI());
                response.sendRedirect("/captcha");
            } else {
                chain.doFilter(request, response);
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    private void sendCaptcha(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String storedCode = (String) session.getAttribute("code");
        if (storedCode == null) {
            int code = genCode();
            storedCode = String.valueOf(code);
            session.setAttribute("code", storedCode);
        }
        response.getWriter().print(getCaptchaForm(storedCode));
        response.getWriter().flush();
    }

    private void checkCaptcha(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String captcha = request.getParameter("User-Captcha");
        if (captcha != null) {
            Object rightAnswer = session.getAttribute("code");
            if (Objects.equals(rightAnswer, captcha)) {
                session.setAttribute("passedCaptcha", "true");
            }
        }
        session.removeAttribute("code");
        response.sendRedirect("/captcha");
    }

    private int genCode() {
        return random.nextInt(900) + 100;
    }
}