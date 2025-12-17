package kz.rssession.commons.dto.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import kz.rssession.commons.enums.PreferredContact;
import kz.rssession.commons.enums.TariffType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserRequestDto {

    @NotBlank(message = "Password is required")
    private String password;

    @Email(message = "Email must be valid")
    @NotBlank(message = "Email is required")
    private String email;

    private String phoneNumber;

    @NotNull(message = "Preferred contact is required")
    private PreferredContact preferredContact;

    @NotNull(message = "Tariff type is required")
    private TariffType tariffType;
}

