package org.example.service;

import org.example.dto.types.TypesCreateDTO;
import org.example.dto.types.TypesDTO;
import org.example.entity.TypesEntity;
import org.example.enums.LanguageEnum;
import org.example.repository.TypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;


@Service
public class TypesService {
    @Autowired
    private TypesRepository typesRepository;

    public TypesDTO create(TypesCreateDTO createDTO) {
        TypesEntity entity = new TypesEntity();
        entity.setOrderNumber(createDTO.getOrderNumber());
        entity.setNameUz(createDTO.getNameUz());
        entity.setNameEn(createDTO.getNameEn());
        entity.setNameRu(createDTO.getNameRu());
        typesRepository.save(entity);

        return toDTO(entity);
    }

    public Boolean updaate(Integer id, TypesCreateDTO createDTO) {
        TypesEntity entity = byId(id);
        entity.setNameUz(createDTO.getNameUz());
        entity.setNameEn(createDTO.getNameEn());
        entity.setNameRu(createDTO.getNameRu());
        entity.setOrderNumber(createDTO.getOrderNumber());
        typesRepository.save(entity);
        return true;
    }

    public Boolean delete(Integer id) {
        typesRepository.deleteById(id);
        return true;
    }

    public PageImpl<TypesDTO> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<TypesEntity> typesEntities = typesRepository.findAll(pageable);
        List<TypesDTO> typesDTOList = new LinkedList<>();
        for (TypesEntity typesEntity : typesEntities.getContent()) {
            typesDTOList.add(toDTO(typesEntity));
        }
        return new PageImpl<>(typesDTOList, pageable, typesEntities.getTotalElements());
    }

    public TypesEntity byId(Integer id) {
        return typesRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("Id not found");
        });
    }

    public TypesDTO toDTO(TypesEntity entity) {
        TypesDTO dto = new TypesDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setNameRu(entity.getNameRu());
        dto.setNameUz(entity.getNameUz());
        dto.setNameEn(entity.getNameEn());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setVisible(entity.getVisible());
        return dto;
    }

    public List<TypesDTO> getAllByLang(LanguageEnum lang) {
        Iterable<TypesEntity> entities = typesRepository.findAllByVisibleTrueOrderByOrderNumberDesc();
        List<TypesDTO> dtoList = new LinkedList<>();
        for (TypesEntity entity : entities) {
            TypesDTO dto = new TypesDTO();
            dto.setId(entity.getId());
            switch (lang) {
                case EN -> dto.setName(entity.getNameEn());
                case UZ -> dto.setName(entity.getNameUz());
                case RU -> dto.setName(entity.getNameRu());
            }
            dtoList.add(dto);
        }
        return dtoList;
    }
}

