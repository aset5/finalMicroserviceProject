package kz.rssession.commons.dto.post;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostWithStatsResponseDto {

    private UUID id;
    private UUID authorId;

    private String content;
    private List<UUID> mediaFileIds;

    private long likesCount;
    private long commentsCount;

    private LocalDateTime publishedAt;
    private LocalDateTime createdAt;
}
