package org.example.controller;

import jakarta.validation.Valid;
import org.example.dto.types.TypesCreateDTO;
import org.example.dto.types.TypesDTO;
import org.example.enums.LanguageEnum;
import org.example.enums.ProfileRole;
import org.example.service.TypesService;
import org.example.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("types")
public class TypesController {
    @Autowired
    private TypesService typesService;

    @PostMapping("/create")
    public ResponseEntity<TypesDTO> create(@Valid @RequestBody TypesCreateDTO createDTO,
                                           @RequestHeader("Authorization") String token){
        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok().body(typesService.create(createDTO));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Boolean> update(@PathVariable("id") Integer id,
                                          @RequestBody TypesCreateDTO createDTO,
                                          @RequestHeader("Authorization") String token){
        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok().body(typesService.updaate(id,createDTO));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id,
                                          @RequestHeader("Authorization") String token){
        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
        return ResponseEntity.ok().body(typesService.delete(id));
    }
    @GetMapping("/pagination")
    public ResponseEntity<PageImpl<TypesDTO>> pagination(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                                         @RequestParam(value = "size",defaultValue = "10") Integer size,
                                                         @RequestHeader("Authorization") String token){
        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
        PageImpl<TypesDTO> response = typesService.pagination(page-1,size);
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/lang")
    public ResponseEntity<List<TypesDTO>> getAll(@RequestHeader(value = "Accept-Language",defaultValue = "UZ") LanguageEnum lang){
        return ResponseEntity.ok().body(typesService.getAllByLang(lang));
    }

}
