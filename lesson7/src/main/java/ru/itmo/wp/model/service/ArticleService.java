package ru.itmo.wp.model.service;

import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.UserRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;
import ru.itmo.wp.model.repository.impl.UserRepositoryImpl;

import java.util.ArrayList;
import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();
    private final UserRepository userRepository = new UserRepositoryImpl();

    public static class ArticleView {
        private final Article article;
        private final User user;

        private ArticleView(Article article, User user) {
            this.article = article;
            this.user = user;
        }

        public Article getArticle() {
            return article;
        }

        public User getUser() {
            return user;
        }
    }

    public void validate(Article article) throws ValidationException {
        String title = article.getTitle();
        String text = article.getText();

        if (title == null || title.trim().isEmpty()) {
            throw new ValidationException("Title can not be empty");
        } else if (title.length() > 255) {
            throw new ValidationException("Title can't be longer than 255 characters");
        }

        if (text == null || text.trim().isEmpty()) {
            throw new ValidationException("Text can not be empty");
        } else if (text.length() > 65000) {
            throw new ValidationException("Text can't be longer than 65000 characters");
        }

        if (userRepository.find(article.getUserId()) == null) {
            throw new ValidationException("Can't find user");
        }
    }

    public void save(Article talk) {
        articleRepository.save(talk);
    }

    public List<Article> findAllByUserId(long id) {
        return articleRepository.findAllByUserId(id);
    }

    public List<ArticleView> findAllVisible() {
        List<ArticleView> visible = new ArrayList<>();
        List<Article> all = articleRepository.findAll();
        for (Article article : all) {
            if (!article.isHidden()) {
                visible.add(new ArticleView(article, userRepository.find(article.getUserId())));
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
