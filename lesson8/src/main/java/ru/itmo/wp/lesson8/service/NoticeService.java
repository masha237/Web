package ru.itmo.wp.lesson8.service;

import org.springframework.stereotype.Service;
import ru.itmo.wp.lesson8.domain.Notice;
import ru.itmo.wp.lesson8.domain.User;
import ru.itmo.wp.lesson8.repository.NoticeRepository;
import ru.itmo.wp.lesson8.repository.UserRepository;

import java.util.List;

@Service
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public NoticeService(NoticeRepository noticeRepository) {
        this.noticeRepository = noticeRepository;
    }

    public User findById(Long id) {
        return id == null ? null : noticeRepository.findById(id).orElse(null);
    }


    public List<Notice> findAll() {
        return noticeRepository.findAllByOrderByIdDesc();
    }
}
