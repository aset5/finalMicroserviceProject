package kz.rssession.postservice.service;

import kz.rssession.commons.dto.post.CreatePostRequestDto;
import kz.rssession.commons.dto.post.PostResponseDto;
import kz.rssession.commons.dto.post.PostWithStatsResponseDto;
import kz.rssession.commons.enums.LikeTargetType;
import kz.rssession.commons.enums.PostStatus;
import kz.rssession.postservice.entity.LikeEntity;
import kz.rssession.postservice.entity.PostEntity;
import kz.rssession.postservice.repository.CommentRepository;
import kz.rssession.postservice.repository.LikeRepository;
import kz.rssession.postservice.repository.PostMediaRepository;
import kz.rssession.postservice.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMediaRepository postMediaRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public PostResponseDto createPost(UUID authorId, CreatePostRequestDto request) {

        PostEntity post = PostEntity.builder()
                .id(UUID.randomUUID())
                .authorId(authorId)
                .content(request.getContent())
                .postStatus(PostStatus.ACTIVE)
                .publishedAt(LocalDateTime.now())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        postRepository.save(post);

        if (request.getMediaFileIds() != null) {
            request.getMediaFileIds().forEach(fileId ->
                    postMediaRepository.save(post.getId(), fileId)
            );
        }

        return mapToPostResponse(post, request.getMediaFileIds());
    }

    @Override
    public PostWithStatsResponseDto getPostById(UUID postId) {

        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        long likes = likeRepository.countByContent(
                postId,
                LikeTargetType.POST
        );

        long comments = commentRepository.countByPostId(postId);

        return mapToPostWithStats(post, likes, comments);
    }

    @Override
    public List<PostWithStatsResponseDto> getUserPosts(UUID userId) {

        return postRepository.findByAuthorId(userId).stream()
                .map(post -> {

                    long likes = likeRepository.countByContent(
                            post.getId(),
                            LikeTargetType.POST
                    );

                    long comments = commentRepository.countByPostId(post.getId());

                    return mapToPostWithStats(post, likes, comments);
                })
                .toList();
    }

    @Override
    @Transactional
    public void archivePost(UUID userId, UUID postId) {

        PostEntity post = getPostOwnedByUser(userId, postId);
        post.setPostStatus(PostStatus.ARCHIVED);
        post.setUpdatedAt(LocalDateTime.now());
    }

    @Override
    @Transactional
    public void deletePost(UUID userId, UUID postId) {

        PostEntity post = getPostOwnedByUser(userId, postId);
        post.setPostStatus(PostStatus.DELETED);
        post.setUpdatedAt(LocalDateTime.now());
    }

    private PostEntity getPostOwnedByUser(UUID userId, UUID postId) {

        PostEntity post = postRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        if (!post.getAuthorId().equals(userId)) {
            throw new RuntimeException("Access denied");
        }
        return post;
    }

    private PostResponseDto mapToPostResponse(PostEntity post, List<UUID> mediaIds) {
        return PostResponseDto.builder()
                .id(post.getId())
                .authorId(post.getAuthorId())
                .content(post.getContent())
                .mediaFileIds(mediaIds)
                .postStatus(post.getPostStatus())
                .publishedAt(post.getPublishedAt())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    private PostWithStatsResponseDto mapToPostWithStats(
            PostEntity post,
            long likes,
            long comments
    ) {
        return PostWithStatsResponseDto.builder()
                .id(post.getId())
                .authorId(post.getAuthorId())
                .content(post.getContent())
                .likesCount(likes)
                .commentsCount(comments)
                .publishedAt(post.getPublishedAt())
                .createdAt(post.getCreatedAt())
                .build();
    }
}