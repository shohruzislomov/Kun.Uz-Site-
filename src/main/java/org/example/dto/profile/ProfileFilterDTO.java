package org.example.dto.profile;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.ProfileRole;

import java.time.LocalDateTime;
@Getter
@Setter
public class ProfileFilterDTO {
    private String name;
    private String surname;
    private String phone;
    private ProfileRole role;
    private LocalDateTime created_date_from;
    private LocalDateTime created_date_to;
}
