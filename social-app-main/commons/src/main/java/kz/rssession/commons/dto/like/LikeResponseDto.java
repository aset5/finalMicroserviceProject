package kz.rssession.commons.dto.like;

import kz.rssession.commons.enums.LikeTargetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeResponseDto {

    private UUID id;
    private UUID userId;

    private UUID contentId;
    private LikeTargetType contentType;

    private LocalDateTime createdAt;


}
