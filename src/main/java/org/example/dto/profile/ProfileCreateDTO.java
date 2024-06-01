package org.example.dto.profile;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.example.enums.ProfileRole;
import org.example.enums.ProfileStatus;
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProfileCreateDTO {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private ProfileStatus status;
    private ProfileRole role;
}
