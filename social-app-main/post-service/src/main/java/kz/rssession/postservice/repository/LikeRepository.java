package kz.rssession.postservice.repository;

import io.lettuce.core.dynamic.annotation.Param;
import kz.rssession.commons.enums.LikeTargetType;
import kz.rssession.postservice.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface LikeRepository extends JpaRepository<LikeEntity, UUID> {

    @Query("""
        SELECT COUNT(l)
        FROM LikeEntity l
        WHERE l.contentId = :contentId
          AND l.contentType = :contentType
    """)
    long countByContent(
            @Param("contentId") UUID contentId,
            @Param("contentType") LikeTargetType contentType
    );

    @Query("""
        SELECT COUNT(l) > 0
        FROM LikeEntity l
        WHERE l.userId = :userId
          AND l.contentId = :contentId
          AND l.contentType = :contentType
    """)
    boolean exists(
            @Param("userId") UUID userId,
            @Param("contentId") UUID contentId,
            @Param("contentType") LikeTargetType contentType
    );

    @Modifying
    @Query("""
        DELETE FROM LikeEntity l
        WHERE l.userId = :userId
          AND l.contentId = :contentId
          AND l.contentType = :contentType
    """)
    void delete(
            @Param("userId") UUID userId,
            @Param("contentId") UUID contentId,
            @Param("contentType") LikeTargetType contentType
    );
}