package org.example.repository;

import jakarta.transaction.Transactional;
import org.example.dto.profile.ProfileUpdateDTO;
import org.example.entity.ProfileEntity;
import org.example.enums.ProfileStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer> {

    Page<ProfileEntity> findAll(Pageable pageable);

    Optional<ProfileEntity> findByEmailAndVisibleTrue(String email);

    @Transactional
    @Modifying
    @Query(" update ProfileEntity set status = ?2 where id = ?1 ")
    int updateStatus(Integer id, ProfileStatus status);
}

