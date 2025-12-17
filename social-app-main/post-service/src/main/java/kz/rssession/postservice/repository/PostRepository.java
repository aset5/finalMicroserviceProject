package kz.rssession.postservice.repository;

import kz.rssession.commons.enums.PostStatus;
import kz.rssession.postservice.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PostRepository extends JpaRepository<PostEntity, UUID> {

    List<PostEntity> findByAuthorId(UUID authorId);

    List<PostEntity> findByAuthorIdAndPostStatus(UUID authorId, PostStatus status);
}