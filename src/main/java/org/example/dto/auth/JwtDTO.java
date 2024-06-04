package org.example.dto.auth;

import lombok.Getter;
import lombok.Setter;
import org.example.enums.ProfileRole;
@Getter
@Setter
public class JwtDTO {
    private Integer id;
    private String username;
    private ProfileRole role;

    public JwtDTO(Integer id, ProfileRole role) {
        this.id = id;
        this.role = role;
    }
    public JwtDTO(Integer id) {
        this.id = id;
    }
}
