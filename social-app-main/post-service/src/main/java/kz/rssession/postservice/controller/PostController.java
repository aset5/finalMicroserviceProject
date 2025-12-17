package kz.rssession.postservice.controller;

import kz.rssession.commons.dto.post.CreatePostRequestDto;
import kz.rssession.commons.dto.post.PostResponseDto;
import kz.rssession.commons.dto.post.PostWithStatsResponseDto;
import kz.rssession.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PostResponseDto createPost(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestBody CreatePostRequestDto request
    ) {
        return postService.createPost(userId, request);
    }

    @GetMapping("/{postId}")
    public PostWithStatsResponseDto getPostById(
            @PathVariable UUID postId
    ) {
        return postService.getPostById(postId);
    }

    @GetMapping("/user/{userId}")
    public List<PostWithStatsResponseDto> getUserPosts(
            @PathVariable UUID userId
    ) {
        return postService.getUserPosts(userId);
    }

    @PutMapping("/{postId}/archive")
    public void archivePost(
            @RequestHeader("X-User-Id") UUID userId,
            @PathVariable UUID postId
    ) {
        postService.archivePost(userId, postId);
    }

    @DeleteMapping("/{postId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(
            @RequestHeader("X-User-Id") UUID userId,
            @PathVariable UUID postId
    ) {
        postService.deletePost(userId, postId);
    }
}