package ru.itmo.wp.model.repository.impl;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.exception.RepositoryException;
import ru.itmo.wp.model.repository.TalksRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TalksRepositoryImpl extends BasicRepositoryImpl<Talk> implements TalksRepository {

    @Override
    public List<Talk> findAllByUserId(long userId) {
        List<Talk> talks = new ArrayList<>();
        try (Connection connection = DATA_SOURCE.getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM Talks WHERE (sourceUserId=? OR targetUserId=?) ORDER BY creationTime DESC")) {
                statement.setLong(1, userId);
                statement.setLong(2, userId);
                try (ResultSet resultSet = statement.executeQuery()) {
                    Talk talk;
                    while ((talk = toRepositoryObject(statement.getMetaData(), resultSet)) != null) {
                        talks.add(talk);
                    }
                }
            }
        } catch (SQLException e) {
            throw new RepositoryException("Can't find Talk.", e);
        }
        return talks;
    }

    @Override
    protected String getTableName() {
        return "Talks";
    }

    @Override
    protected Talk toRepositoryObject(ResultSetMetaData metaData, ResultSet resultSet) throws SQLException {
        if (!resultSet.next()) {
            return null;
        }

        Talk talk = new Talk();
        for (int i = 1; i <= metaData.getColumnCount(); i++) {
            switch (metaData.getColumnName(i)) {
                case "id":
                    talk.setId(resultSet.getLong(i));
                    break;
                case "sourceUserId":
                    talk.setSourceUserId(resultSet.getLong(i));
                    break;
                case "targetUserId":
                    talk.setTargetUserId(resultSet.getLong(i));
                    break;
                case "creationTime":
                    talk.setCreationTime(resultSet.getTimestamp(i));
                    break;
                case "text":
                    talk.setText(resultSet.getString(i));
                    break;
                default:
                    // No operations.
            }
        }

        return talk;
    }
}