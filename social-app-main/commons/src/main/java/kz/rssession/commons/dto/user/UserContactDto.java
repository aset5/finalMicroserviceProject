package kz.rssession.commons.dto.user;

import kz.rssession.commons.enums.PreferredContact;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserContactDto {
    private String email;
    private String phoneNumber;
    private PreferredContact preferredContact;
}
