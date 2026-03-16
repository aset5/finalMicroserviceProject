package kz.digital_portal.auth.dto;


import kz.digital_portal.auth.model.Role;
import lombok.Data;

@Data
public class RegistrationRequest {
    private String email;
    private String password;
    private String fullName;
    private Role role;
    private Long universityId; // Будет заполняться только студентом
}
