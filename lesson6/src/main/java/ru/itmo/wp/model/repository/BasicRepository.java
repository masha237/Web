package ru.itmo.wp.model.repository;

import java.util.List;

public interface BasicRepository<T> {

    T find(long id);
    List<T> findAll();
    int findCount();
    void save(T user, Object... args);

    T findBy(Object... args);
}
