package kz.rssession.postservice.repository;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class PostMediaRepositoryImpl implements PostMediaRepository {

    private final EntityManager entityManager;

    @Override
    public void save(UUID postId, UUID fileId) {
        entityManager.createNativeQuery(
                        "INSERT INTO post_media(post_id, file_id) VALUES (:postId, :fileId)"
                )
                .setParameter("postId", postId)
                .setParameter("fileId", fileId)
                .executeUpdate();
    }

    @Override
    public List<UUID> findFileIdsByPostId(UUID postId) {
        return entityManager.createNativeQuery(
                        "SELECT file_id FROM post_media WHERE post_id = :postId", UUID.class
                )
                .setParameter("postId", postId)
                .getResultList();
    }

    @Override
    public void deleteByPostId(UUID postId) {
        entityManager.createNativeQuery(
                        "DELETE FROM post_media WHERE post_id = :postId"
                )
                .setParameter("postId", postId)
                .executeUpdate();
    }
}
