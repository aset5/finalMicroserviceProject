package kz.rssession.commons.dto.user;

import jakarta.validation.constraints.Email;
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
public class UpdateUserRequestDto {

    private String password;

    @Email(message = "Email must be valid")
    private String email;

    private String phoneNumber;

    private PreferredContact preferredContact;

    private TariffType tariffType;
}

