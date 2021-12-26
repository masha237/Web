package ru.itmo.wp.web.page;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@SuppressWarnings({"unused", "RedundantSuppression"})
public class IndexPage extends Page {
    private void action(HttpServletRequest request, Map<String, Object> view) {
        putMessage(request, view);
        findAllArticlesVisible(request, view);
    }
    private void findAllArticlesVisible(HttpServletRequest request, Map<String, Object> view) {
        view.put("articleViews", articleService.findAllVisible());
    }
}
