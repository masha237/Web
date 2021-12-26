package ru.itmo.wp.model.repository;

import ru.itmo.wp.model.domain.User;


public interface UserRepository extends BasicRepository<User> {

    User findByLogin(String login);
    User findByLoginAndPasswordSha(String login, String passwordSha);
    void save(User user, String passwordSha);
    void setAdminAuthorities(long id, boolean hidden);

}
