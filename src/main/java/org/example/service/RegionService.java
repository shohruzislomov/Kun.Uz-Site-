package org.example.service;


import org.example.dto.region.RegionCreateDTO;
import org.example.dto.region.RegionDTO;
import org.example.entity.RegionEntity;
import org.example.enums.LanguageEnum;
import org.example.mapper.RegionMapper;
import org.example.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public RegionDTO create(RegionCreateDTO regionCreateDTO) {
        RegionEntity entity = new RegionEntity();
        entity.setOrderNumber(regionCreateDTO.getOrderNumber());
        entity.setNameUz(regionCreateDTO.getNameUz());
        entity.setNameEn(regionCreateDTO.getNameEn());
        entity.setNameRu(regionCreateDTO.getNameRu());
        regionRepository.save(entity);

        return toDTO(entity);
    }

    public List<RegionDTO> getAll() {
        Iterable<RegionEntity> entities = regionRepository.findAll();
        List<RegionDTO> dtos = new LinkedList<>();
        for (RegionEntity regionEntity : entities) {
            dtos.add(toDTO(regionEntity));
        }
        return dtos;
    }

    public List<RegionDTO> getAllByLang(LanguageEnum lang) {
        Iterable<RegionEntity> iterable = regionRepository.findAllByVisibleTrueOrderByOrderNumberDesc();
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity entity : iterable) {
            RegionDTO dto = new RegionDTO();
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

    public List<RegionDTO> getAllByLang2(LanguageEnum lang) {
        List<RegionMapper> mapperList = regionRepository.findAll(lang.name());
        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionMapper entity : mapperList) {
            RegionDTO dto = new RegionDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dtoList.add(dto);
        }
        return dtoList;
    }

    public Boolean update(Integer id, RegionCreateDTO dto) {
        RegionEntity region = getId(id);
        region.setOrderNumber(dto.getOrderNumber());
        region.setNameUz(dto.getNameUz());
        region.setNameEn(dto.getNameEn());
        region.setNameRu(dto.getNameRu());
        regionRepository.save(region);
        return true;
    }

    public Boolean delete(Integer id) {
        regionRepository.deleteById(id);
        return true;
    }

    public RegionEntity getId(Integer id) {
        return regionRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException(" Id not found ");
        });
    }

    public RegionDTO toDTO(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getNameUz());
        dto.setNameEn(entity.getNameEn());
        dto.setNameRu(entity.getNameRu());
        dto.setOrderNumber(entity.getOrderNumber());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

}

