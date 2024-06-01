package org.example.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.example.dto.profile.ProfileFilterDTO;
import org.example.entity.ProfileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ProfileCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public List<ProfileEntity> filter(ProfileFilterDTO filter){
        Map<String, Object> params = new HashMap<>();
        StringBuilder query = new StringBuilder();

        if (filter.getName() != null) {
            query.append(" and name=:name ");
            params.put("name", filter.getName());
        }
        if (filter.getSurname() != null){
            query.append(" and surname=:surname ");
            params.put("surname", filter.getSurname());
        }
        if (filter.getPhone() != null){
            query.append(" and phone=:phone ");
            params.put("phone", filter.getPhone());
        }
        if (filter.getRole() != null){
            query.append(" and role =:role ");
            params.put("role", filter.getRole().toString());
        }
        if (filter.getCreated_date_from() != null){
            query.append(" and created_date_from=:createdDateFrom ");
            params.put("createdDateFrom", filter.getCreated_date_from());
        }
        if (filter.getCreated_date_to() != null){
            query.append(" and created_date_to=:createdDateTo ");
            params.put("createdDateFrom", filter.getCreated_date_to());
        }

        StringBuilder selectSql = new StringBuilder("select * from profile where visible = true ");
        selectSql.append(query);
        Query selectQuery = entityManager.createNativeQuery(selectSql.toString());

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            selectQuery.setParameter(entry.getKey(), entry.getValue());
        }

        List<ProfileEntity> profileEntityList = selectQuery.getResultList();
        return profileEntityList;
    }
//    phone,role,created_date_from,created_date_to

}
