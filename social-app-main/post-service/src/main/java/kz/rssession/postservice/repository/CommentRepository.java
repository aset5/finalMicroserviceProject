package kz.rssession.postservice.repository;

import kz.rssession.postservice.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface CommentRepository extends JpaRepository<CommentEntity, UUID> {

    List<CommentEntity> findByPostId(UUID postId);

    @Query("SELECT COUNT(c) FROM CommentEntity c WHERE c.postId = :postId")
    long countByPostId(UUID postId);
}