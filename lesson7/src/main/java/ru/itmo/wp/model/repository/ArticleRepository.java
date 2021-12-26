package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;

import java.util.List;

public interface ArticleRepository extends BasicRepository<Article> {
    void save(Article article);

    @Override
    List<Article> findAll();

    void setHidden(long id, boolean hidden);

    List<Article> findAllByUserId(long id);
}
