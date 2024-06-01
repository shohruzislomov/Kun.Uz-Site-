package org.example.service;

import org.example.dto.category.CategoryCreateDTO;
import org.example.dto.category.CategoryDTO;
import org.example.dto.region.RegionDTO;
import org.example.entity.CategoryEntity;
import org.example.entity.RegionEntity;
import org.example.enums.LanguageEnum;
import org.example.exception.AppBadException;
import org.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO create(CategoryCreateDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setNameUz(dto.getNameUz());
        categoryRepository.save(entity);
        return toDo(entity);
    }

    public CategoryDTO toDo(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setNameUz(entity.getNameUz());
        dto.setNameEn(entity.getNameEn());
        dto.setNameRu(entity.getNameRu());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setVisible(entity.getVisible());
        return dto;
    }

    public CategoryDTO update(Integer id, CategoryCreateDTO dto) {
        Optional<CategoryEntity> optional = categoryRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadException("There is no such category");
        }
        CategoryEntity entity = optional.get();
        entity.setOrderNumber(dto.getOrderNumber());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setNameUz(dto.getNameUz());
        entity.setNameUz(dto.getNameUz());
        categoryRepository.save(entity);
        return toDo(entity);
    }

    public String delete(Integer id) {
        categoryRepository.deleteById(id);
        return "Deleted Category";
    }

    public List<CategoryDTO> getAllByLang(LanguageEnum lang) {
        Iterable<CategoryEntity> list = categoryRepository.findAllByVisibleTrueOrderByOrderNumberDesc();
        List<CategoryDTO> dtos = new LinkedList<>();
        for (CategoryEntity entity : list) {
            CategoryDTO dto = new CategoryDTO();
            dto.setId(entity.getId());
            dto.setOrderNumber(entity.getOrderNumber());
            dto.setNameEn(entity.getNameEn());
            dto.setNameRu(entity.getNameRu());
            dto.setNameUz(entity.getNameUz());
            switch (lang) {
                case EN -> dto.setName(entity.getNameEn());
                case UZ -> dto.setName(entity.getNameUz());
                case RU -> dto.setName(entity.getNameRu());
            }
            dtos.add(dto);
        }
        return dtos;
    }

    public List<CategoryDTO> getAll() {
        Iterable<CategoryEntity> list = categoryRepository.findAll();
        List<CategoryDTO> dtos = new LinkedList<>();
        for (CategoryEntity entity : list) {
            dtos.add(toDo(entity));
        }
        return dtos;
    }
}
