package kz.rssession.commons.dto.user;

import kz.rssession.commons.enums.PreferredContact;
import kz.rssession.commons.enums.TariffType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDto {

    private UUID id;
    private String email;
    private String phoneNumber;
    private PreferredContact preferredContact;
    private TariffType tariffType;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

