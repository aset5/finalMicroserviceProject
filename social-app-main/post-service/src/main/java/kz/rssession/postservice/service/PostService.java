package kz.rssession.postservice.service;


import kz.rssession.commons.dto.post.CreatePostRequestDto;
import kz.rssession.commons.dto.post.PostResponseDto;
import kz.rssession.commons.dto.post.PostWithStatsResponseDto;

import java.util.List;
import java.util.UUID;

public interface PostService {

    PostResponseDto createPost(UUID authorId, CreatePostRequestDto request);

    PostWithStatsResponseDto getPostById(UUID postId);

    List<PostWithStatsResponseDto> getUserPosts(UUID userId);

    void archivePost(UUID userId, UUID postId);

    void deletePost(UUID userId, UUID postId);
}