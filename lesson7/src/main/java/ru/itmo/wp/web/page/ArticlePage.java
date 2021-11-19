package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ArticlePage extends Page {
    private void action(HttpServletRequest request, Map<String, Object> view) {
        User user = getUser();
        if (user == null) {
            setMessage("Article are available only for logged users");
            throw new RedirectException("/index");
        }
    }

    private void addArticle(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        action(request, view);
        articleService.validateArticle(request.getParameter("title"), request.getParameter("text"));
        Article article = new Article();
        article.setText(request.getParameter("text"));
        article.setTitle(request.getParameter("title"));
        article.setUserId(getUser().getId());
        article.setLogin(getUser().getLogin());
        article.setHidden(false);
        articleService.addArticle(article);

        setMessage("Article create!");
        throw new RedirectException("/index");
    }

}
