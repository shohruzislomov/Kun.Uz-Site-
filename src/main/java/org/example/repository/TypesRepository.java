package org.example.repository;

import org.example.entity.TypesEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TypesRepository extends CrudRepository<TypesEntity,Integer> {
    Page<TypesEntity> findAll(Pageable pageable);
    List<TypesEntity> findAllByVisibleTrueOrderByOrderNumberDesc();

}
