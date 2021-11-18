package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.Article;

import java.util.List;

public interface ArticleRepository extends BasicRepository<Article> {
    void save(Article article);

    @Override
    List<Article> findAll();

    public List<Article> findAllByUserId(long id);
}
