package kz.rssession.postservice.repository;

import java.util.List;
import java.util.UUID;

public interface PostMediaRepository {

    void save(UUID postId, UUID fileId);

    List<UUID> findFileIdsByPostId(UUID postId);

    void deleteByPostId(UUID postId);
}