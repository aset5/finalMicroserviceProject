package kz.rssession.commons.dto.comment;

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
public class CommentResponseDto {

    private UUID id;
    private UUID postId;
    private UUID authorId;

    private String content;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
