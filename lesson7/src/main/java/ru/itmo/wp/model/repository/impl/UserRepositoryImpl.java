package ru.itmo.wp.model.repository.impl;

import ru.itmo.wp.model.domain.User;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.UserRepository;

import java.sql.*;

public class UserRepositoryImpl extends BasicRepositoryImpl<User> implements UserRepository {

    @Override
    public User findByLogin(String login) {
        return findBy("login", login);
    }

    @Override
    public User findByLoginAndPasswordSha(String login, String passwordSha) {
        return findBy("login", login, "passwordSha", passwordSha);
    }

    @Override
    protected String getTableName() {
        return "User";
    }

    @Override
    protected User toRepositoryObject(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        User user = new User();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    user.setId(resultSet.getLong(i));
                    break;
                case "login":
                    user.setLogin(resultSet.getString(i));
                    break;
                case "creationTime":
                    user.setCreationTime(resultSet.getTimestamp(i));
                    break;
                case "admin":
                    user.setAdmin(resultSet.getBoolean(i));
                    break;
                default:
                    // No operations.
            }
        }

        return user;
    }

    @Override
    public void setAdminAuthorities(long id, boolean admin) {
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("UPDATE User SET admin=? WHERE id=?")) {
                statement.setBoolean(1, admin);
                statement.setLong(2, id);
                try {
                    ResultSet resultSet = statement.executeQuery();
                } catch (Exception ignored) {
                    throw new RepositoryException("Unable to set hidden property [id=" + id + "].");
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Article.", e);
        }
    }
    @Override
    public void save(User user, String passwordSha) {
        save(user, "passwordSha", passwordSha);
    }

}

