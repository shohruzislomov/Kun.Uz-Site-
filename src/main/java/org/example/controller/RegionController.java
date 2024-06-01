package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.auth.JwtDTO;
import org.example.dto.region.RegionCreateDTO;
import org.example.dto.region.RegionDTO;
import org.example.enums.LanguageEnum;
import org.example.enums.ProfileRole;
import org.example.service.RegionService;
import org.example.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;

    @PostMapping("/create")
    public ResponseEntity<RegionDTO> create(@Valid @RequestBody RegionCreateDTO createDTO,
                                            @RequestHeader("Authorization") String token) {
        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
        RegionDTO response = regionService.create(createDTO);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegionDTO>> getAll(@RequestHeader("Authorization") String token) {
        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok().body(regionService.getAll());
    }

    @GetMapping("/lang")
    public ResponseEntity<List<RegionDTO>> getAll(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") LanguageEnum lang) {
        return ResponseEntity.ok().body(regionService.getAllByLang(lang));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id,
                                          @RequestBody RegionCreateDTO createDTO,
                                          @RequestHeader("Authorization") String token) {
        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok().body(regionService.update(id, createDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id,
                                          @RequestHeader("Authorization") String token ){
        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok().body(regionService.delete(id));
    }


}
