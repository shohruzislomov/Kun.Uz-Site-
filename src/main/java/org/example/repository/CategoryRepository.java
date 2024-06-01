package org.example.repository;

import org.example.entity.CategoryEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity,Integer> {
    Iterable<CategoryEntity> findAllByVisibleTrueOrderByOrderNumberDesc();
}
