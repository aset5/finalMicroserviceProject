package kz.rssession.postservice.entity;

import jakarta.persistence.*;
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
@Entity
@Table(
        name = "likes",
        uniqueConstraints = {
                @UniqueConstraint(
                        columnNames = {"user_id", "content_id", "content_type"}
                )
        }
)
public class LikeEntity {

    @Id
    private UUID id;

    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(name = "content_id", nullable = false)
    private UUID contentId;

    @Enumerated(EnumType.STRING)
    @Column(name = "content_type", nullable = false)
    private LikeTargetType contentType;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
