package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Article;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.ArticleRepository;
import ru.itmo.wp.model.repository.impl.ArticleRepositoryImpl;

import java.util.List;

public class ArticleService {
    private final ArticleRepository articleRepository = new ArticleRepositoryImpl();

    public void validateArticle(String title, String text) throws ValidationException {
        if (Strings.isNullOrEmpty(title)) {
            throw new ValidationException("Title can not be empty");
        } else if (title.length() > 255) {
            throw new ValidationException("Title too long");
        }
        if (Strings.isNullOrEmpty(text)) {
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
}
