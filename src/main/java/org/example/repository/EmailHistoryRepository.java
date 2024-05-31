package org.example.repository;

import org.example.entity.EmailHistoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface EmailHistoryRepository extends CrudRepository<EmailHistoryEntity,Integer> {
    Long countByEmailAndCreateTimeBetween(String email, LocalDateTime from, LocalDateTime to);

    Optional<EmailHistoryEntity> findByEmail(String email);
}
