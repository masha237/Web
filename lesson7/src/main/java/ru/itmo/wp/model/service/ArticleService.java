package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validateArticle(String title, String text) throws ValidationException {
        if (title == null || title.trim().equals("")) {
            throw new ValidationException("Title can not be empty");
        } else if (title.length() > 255) {
            throw new ValidationException("Title too long");
        }
        if (text == null || text.trim().equals("")) {
            throw new ValidationException("Text can not be empty");
        }
    }

    public void addArticle(Article talk) {
        articleRepository.save(talk);
    }

    public List<Article> findAllByUserId(long id) {
        return articleRepository.findAllByUserId(id);
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public List<Article> findAllVisible() {
        /*return findAll();*/
        List<Article> visible = new ArrayList<>();
        List<Article> all = articleRepository.findAll();
        for (Article article: all) {
            if (!article.isHidden()) {
                visible.add(article);
            }
        }
        return visible;
    }
    public void validateChangeArticle(User user, Article article) throws ValidationException {
        if (user.getId() != article.getUserId()) {
            throw new ValidationException("You can't change this article, you are not creator of it.");
        }
    }

    public Article find(long id) {
        return articleRepository.find(id);
    }

    public void setHiddenProp(long id, boolean hidden) {
        articleRepository.setHiddenProp(id, hidden);
    }
}
