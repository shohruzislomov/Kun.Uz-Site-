package org.example.service;

import org.example.dto.auth.JwtDTO;
import org.example.dto.profile.ProfileCreateDTO;
import org.example.dto.profile.ProfileDTO;
import org.example.dto.profile.ProfileFilterDTO;
import org.example.dto.profile.ProfileUpdateDTO;
import org.example.entity.ProfileEntity;
import org.example.enums.ProfileRole;
import org.example.exception.AppBadException;
import org.example.repository.ProfileCustomRepository;
import org.example.repository.ProfileRepository;
import org.example.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private ProfileCustomRepository profileCustomRepository;

    public ProfileDTO create(ProfileCreateDTO profile) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(profile.getName());
        entity.setSurname(profile.getSurname());
        entity.setEmail(profile.getEmail());
        entity.setPhone(profile.getPhone());
        entity.setPassword(MD5Util.getMD5(profile.getPassword()));
        entity.setStatus(profile.getStatus());
        entity.setRole(profile.getRole());
        profileRepository.save(entity);
        return toDTO(entity);
    }

    public void update(Integer id, ProfileCreateDTO profile) {
        ProfileEntity exists = getId(id);
        exists.setName(profile.getName());
        exists.setSurname(profile.getSurname());
        exists.setEmail(profile.getEmail());
        exists.setPhone(profile.getPhone());
        exists.setPassword(profile.getPassword());
        exists.setStatus(profile.getStatus());
        exists.setRole(profile.getRole());
        profileRepository.save(exists);
    }

    public Boolean updateUser(Integer id, ProfileUpdateDTO profileUser) {
        ProfileEntity profileEntity = getId(id);
        profileEntity.setName(profileUser.getFirstName());
        profileEntity.setSurname(profileUser.getLastName());
        profileRepository.save(profileEntity);
        return true;
    }

    public PageImpl<ProfileDTO> pagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ProfileEntity> profileEntities = profileRepository.findAll(pageable);
        List<ProfileDTO> profileList = new ArrayList<>();
        profileEntities.getContent().forEach(profileEntity -> profileList.add(toDTO(profileEntity)));
        return new PageImpl<>(profileList, pageable, profileEntities.getTotalElements());
    }

    public Boolean delete(Integer id) {
        profileRepository.deleteById(id);
        return true;
    }

    public ProfileEntity getId(Integer id) {
        return profileRepository.findById(id).orElseThrow(() -> {
            throw new AppBadException("Profile not found");
        });
    }


    public ProfileDTO toDTO(ProfileEntity profileEntity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(profileEntity.getId());
        dto.setName(profileEntity.getName());
        dto.setSurname(profileEntity.getSurname());
        dto.setEmail(profileEntity.getEmail());
        dto.setPhone(profileEntity.getPhone());
        dto.setPassword(profileEntity.getPassword());
        dto.setStatus(profileEntity.getStatus());
        dto.setRole(profileEntity.getRole());
        return dto;
    }

    public List<ProfileDTO> filter(ProfileFilterDTO profileFilterDTO) {
        Iterable<ProfileEntity> entityList = profileCustomRepository.filter(profileFilterDTO);
        List<ProfileDTO> profileDTOList = new LinkedList<>();
        for (ProfileEntity entity : entityList) {
            ProfileDTO dto = new ProfileDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setEmail(entity.getEmail());
            dto.setPhone(entity.getPhone());
            dto.setPassword(entity.getPassword());
            dto.setStatus(entity.getStatus());
            dto.setRole(entity.getRole());
            profileDTOList.add(dto);
        }
        return profileDTOList;
    }
}
