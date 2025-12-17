package kz.rssession.postservice.service;

import kz.rssession.commons.client.NotificationClient;
import kz.rssession.commons.dto.like.LikeRequestDto;
import kz.rssession.commons.enums.LikeTargetType;
import kz.rssession.commons.enums.NotificationType;
import kz.rssession.commons.event.NotificationEvent;
import kz.rssession.postservice.entity.LikeEntity;
import kz.rssession.postservice.entity.PostEntity;
import kz.rssession.postservice.repository.LikeRepository;
import kz.rssession.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LikeServiceImpl implements LikeService {

    private final NotificationClient notificationClient;

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public void like(UUID userId, LikeRequestDto request) {

        if (likeRepository.exists(
                userId,
                request.getContentId(),
                request.getContentType()
        )) {
            return;
        }

        LikeEntity like = LikeEntity.builder()
                .id(UUID.randomUUID())
                .userId(userId)
                .contentId(request.getContentId())
                .contentType(request.getContentType())
                .createdAt(LocalDateTime.now())
                .build();

        likeRepository.save(like);


        if (request.getContentType() == LikeTargetType.POST) {

            UUID postAuthorId = postRepository.findById(request.getContentId())
                    .map(PostEntity::getAuthorId)
                    .orElse(null);

            if (postAuthorId != null && !postAuthorId.equals(userId)) {
                notificationClient.send(
                        NotificationEvent.builder()
                                .recipientUserId(postAuthorId)
                                .senderUserId(userId)
                                .type(NotificationType.POST_LIKED)
                                .targetId(request.getContentId())
                                .createdAt(LocalDateTime.now())
                                .build()
                );
            }
        }
    }

    @Override
    @Transactional
    public void unlike(UUID userId, LikeRequestDto request) {

        likeRepository.delete(
                userId,
                request.getContentId(),
                request.getContentType()
        );
    }
}