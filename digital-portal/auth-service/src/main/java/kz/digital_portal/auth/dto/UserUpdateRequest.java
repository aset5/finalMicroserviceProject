package kz.digital_portal.auth.dto;

import kz.digital_portal.auth.model.Role;
import lombok.Data;

@Data
public class UserUpdateRequest {
    private String fullName;
    private Role role;
    private boolean enabled;
}