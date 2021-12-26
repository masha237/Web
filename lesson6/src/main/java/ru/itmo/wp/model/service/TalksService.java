package ru.itmo.wp.model.service;

import com.google.common.base.Strings;
import ru.itmo.wp.model.domain.Talk;
import ru.itmo.wp.model.exception.ValidationException;
import ru.itmo.wp.model.repository.TalksRepository;
import ru.itmo.wp.model.repository.impl.TalksRepositoryImpl;

import java.util.List;

public class TalksService {
    private final TalksRepository talksRepository = new TalksRepositoryImpl();

    public void validateTalk(String text, String recipientId) throws ValidationException {
        if (text == null || text.trim().equals("")) {
            throw new ValidationException("Message can not be empty");
        } else if (text.length() > 255) {
            throw new ValidationException("Message too long");
        }
        try {
            Long.parseLong(recipientId);
        } catch (NumberFormatException e) {
            throw new ValidationException("Incorrect recipient");
        }
    }

    public void addTalk(Talk talk) {
        talksRepository.save(talk);
    }

    public List<Talk> findAllByUserId(long id) {
        return talksRepository.findAllByUserId(id);
    }
}
