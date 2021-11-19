package ru.itmo.wp.web.page;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.web.exception.RedirectException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class MyArticlesPage extends Page {
    private void action(HttpServletRequest request, Map<String, Object> view) {
        if (getUser() == null) {
            request.getSession().setAttribute("message", "You should login to see your articles");
            throw new RedirectException("/index");
        }
    }
    private void findAllByUserId(HttpServletRequest request, Map<String, Object> view) {
        view.put("myArticles", articleService.findAllByUserId(getUser().getId()));
    }
    private void changeHiddenProp(HttpServletRequest request, Map<String, Object> view) throws ValidationException {
        User user = getUser();
        long id = Long.parseLong(request.getParameter("articleId"));
        boolean newValue = Boolean.parseBoolean(request.getParameter("newValue"));
        Article articleToChange = articleService.find(id);
        articleService.validateChangeArticle(user, articleToChange);
        if (articleToChange.isHidden() != newValue) {
            articleService.setHiddenProp(id, newValue);
        }
        view.put("currentHidProp", newValue);
    }
}
