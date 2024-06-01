package org.example.dto.types;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TypesCreateDTO {
    @NotNull(message = "OrderNumber required ")
    private Integer orderNumber;
    @NotBlank(message = "NameUz required ")
    private String nameUz;
    @NotBlank(message = "NameRu required ")
    private String nameRu;
    @NotBlank(message = "NameEn required ")
    private String nameEn;
}