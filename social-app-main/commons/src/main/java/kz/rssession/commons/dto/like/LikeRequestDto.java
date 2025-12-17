package kz.rssession.commons.dto.like;

import kz.rssession.commons.enums.LikeTargetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LikeRequestDto {

    private UUID contentId;
    private LikeTargetType contentType;
}