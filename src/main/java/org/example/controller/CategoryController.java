package org.example.controller;

import jakarta.persistence.Id;
import org.example.dto.category.CategoryCreateDTO;
import org.example.dto.category.CategoryDTO;
import org.example.entity.CategoryEntity;
import org.example.enums.LanguageEnum;
import org.example.enums.ProfileRole;
import org.example.service.CategoryService;
import org.example.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryCreateDTO dto,
                                              @RequestHeader("Category") String token) {
        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
        CategoryDTO response = categoryService.create(dto);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable("id") Integer id,
                                              @RequestBody CategoryCreateDTO dto,
                                              @RequestHeader("Category") String token) {
        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
        CategoryDTO response = categoryService.update(id, dto);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Integer id,
                                         @RequestHeader("Category") String token) {
        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
        String response = categoryService.delete(id);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDTO>> getAll(@RequestHeader("Category") String token) {
        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
        List<CategoryDTO> response = categoryService.getAll();
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/getAll")
    public ResponseEntity<List<CategoryDTO>> getAllByLang(@RequestHeader(value = "Accept-Language", defaultValue = "UZ") LanguageEnum lang,
                                                    @RequestHeader("Category") String token) {
        SecurityUtil.getJwtDTO(token, ProfileRole.ROLE_ADMIN);
        List<CategoryDTO> response = categoryService.getAllByLang(lang);
        return ResponseEntity.ok().body(response);
    }

}
