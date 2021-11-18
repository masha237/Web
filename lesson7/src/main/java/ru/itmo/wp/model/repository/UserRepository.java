package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.User;

import java.util.List;

public interface UserRepository extends BasicRepository<User> {

    User findByLogin(String login);
    User findByLoginAndPasswordSha(String login, String passwordSha);
    void save(User user, String passwordSha);
}
