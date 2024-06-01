package org.example.repository;


import org.example.entity.RegionEntity;
import org.example.mapper.RegionMapper;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegionRepository extends CrudRepository<RegionEntity,Integer> {
    List<RegionEntity> findAllByVisibleTrueOrderByOrderNumberDesc();

    @Query(value = " select id, " +
            " CASE :lang " +
            "   WHEN 'UZ' THEN name_uz " +
            "   WHEN 'EN' THEN name_en " +
            "   WHEN 'RU' THEN name_ru " +
            "  END as name " +
            "from regions order by order_number desc ; ", nativeQuery = true)
    List<RegionMapper> findAll(@Param("lang") String lang);
}
