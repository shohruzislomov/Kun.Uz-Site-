package org.example.service;

import org.example.entity.EmailHistoryEntity;
import org.example.exception.AppBadException;
import org.example.repository.EmailHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EmailHistoryService {
    @Autowired
    private EmailHistoryRepository emailHistoryRepository;

    public void create(String email, String text) {
        EmailHistoryEntity entity = new EmailHistoryEntity();
        entity.setEmail(email);
        entity.setMessage(text);
        emailHistoryRepository.save(entity);
    }
    public void checkEmailLimit(String email) { // 1 -min 3 attempt
        LocalDateTime to = LocalDateTime.now();
        LocalDateTime from = to.minusMinutes(1);

        long count = emailHistoryRepository.countByEmailAndCreateTimeBetween(email, from, to);
        if (count >= 3) {
            throw new AppBadException("Sms limit reached. Please try after some time");
        }
    }
    public void isNotExpired(String email) {
        Optional<EmailHistoryEntity> optional = emailHistoryRepository.findByEmail(email);
        if (optional.isEmpty()) {
            throw new AppBadException("Email history not found");
        }
        EmailHistoryEntity entity = optional.get();
        if (entity.getCreateTime().plusDays(1).isBefore(LocalDateTime.now())) {
            throw new AppBadException("Confirmation time expired");
        }
    }
}
