package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class ArticlePage extends Page {
    private void action(HttpServletRequest request, Map<String, Object> view) {

    }

    private void addArticle(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        Article article = new Article();
        article.setText(request.getParameter("text"));
        article.setTitle(request.getParameter("title"));
        article.setUserId(getUser().getId());
        article.setHidden(false);
        articleService.validate(article);
        articleService.save(article);

        setMessage("Article create!");
        throw new RedirectException("/index");
    }

    @Override
    protected void before(HttpServletRequest request, Map<String, Object> view) {
        super.before(request, view);
        if (getUser() == null) {
            setMessage("Article are available only for logged users");
            throw new RedirectException("/index");
        }
    }
}
