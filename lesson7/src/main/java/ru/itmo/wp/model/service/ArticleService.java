package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.UserRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();

    private static class ArticleView extends Article {
        private String login;

        ArticleView(String title, String text, String login, Date time) {
            this.login = login;
            setText(text);
            setTitle(title);
            setCreationTime(time);
        }

        public void setLogin(String text) {
            this.login = text;
        }

        public String getLogin() {
            return login;
        }
    }

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

    public List<ArticleView> findAllVisible() {
        /*return findAll();*/
        List<ArticleView> visible = new ArrayList<>();
        List<Article> all = articleRepository.findAll();
        for (Article article: all) {
            if (!article.isHidden()) {
                visible.add(new ArticleView(article.getTitle(), article.getText(),
                        userRepository.find(article.getUserId()).getLogin(), article.getCreationTime()));
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

    public void setHidden(long id, boolean hidden) {
        articleRepository.setHidden(id, hidden);
    }
}
