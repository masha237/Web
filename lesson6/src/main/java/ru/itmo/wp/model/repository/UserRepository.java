package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.User;

import java.util.List;

public interface UserRepository extends BasicRepository<User> {

    User findByLogin(String login);
    User findByEmail(String email);
    User findByLoginAndPasswordSha(String login, String passwordSha);
    User findByLoginOrEmailAndPasswordSha(String login, String passwordSha);
    User findByEmailAndPasswordSha(String login, String passwordSha);
    void save(User user, String passwordSha);
    long findUserCount();
}
