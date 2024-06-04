package org.example.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.example.dto.auth.JwtDTO;
import org.example.dto.profile.ProfileCreateDTO;
import org.example.dto.profile.ProfileDTO;
import org.example.dto.profile.ProfileFilterDTO;
import org.example.dto.profile.ProfileUpdateDTO;
import org.example.enums.ProfileRole;
import org.example.service.ProfileService;
import org.example.util.HttpRequestUtil;
import org.example.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping("/admin/create")
    public ResponseEntity<ProfileDTO> create(@Valid @RequestBody ProfileCreateDTO profile) {
        ProfileDTO response = profileService.create(profile);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/admin/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id,
                                          @Valid @RequestBody ProfileCreateDTO profileCreateDTO,
                                          HttpServletRequest request) {
        HttpRequestUtil.getJwtDTO(request,ProfileRole.ROLE_ADMIN);
        profileService.update(id, profileCreateDTO);
        return ResponseEntity.ok().body(true);
    }

    @PutMapping("/updateUser/{userId}")
    public ResponseEntity<Boolean> updateUser(@PathVariable("id") Integer id,
                                              @Valid @RequestBody ProfileUpdateDTO profileUpdateDTO,
                                              HttpServletRequest request) {
        HttpRequestUtil.getJwtDTO(request,ProfileRole.ROLE_ADMIN);
        profileService.updateUser(id, profileUpdateDTO);
        return ResponseEntity.ok().body(true);
    }

    @GetMapping("/admin/pagination")
    public ResponseEntity<PageImpl<ProfileDTO>> pagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                           @RequestParam(value = "size", defaultValue = "10") int size,
                                                           HttpServletRequest request) {
        HttpRequestUtil.getJwtDTO(request,ProfileRole.ROLE_ADMIN);
        PageImpl<ProfileDTO> list = profileService.pagination(page - 1, size);
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id,
                                          HttpServletRequest request) {
        HttpRequestUtil.getJwtDTO(request,ProfileRole.ROLE_ADMIN);
        profileService.delete(id);
        return ResponseEntity.ok().body(true);
    }

    @PostMapping("/filter")
    public ResponseEntity<List<ProfileDTO>> filter(@RequestBody ProfileFilterDTO profileFilterDTO){
        List<ProfileDTO> response = profileService.filter(profileFilterDTO);
        return ResponseEntity.ok().body(response);
    }
}