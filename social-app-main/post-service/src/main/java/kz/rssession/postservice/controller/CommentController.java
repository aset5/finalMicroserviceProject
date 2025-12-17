package kz.rssession.postservice.controller;

import kz.rssession.commons.dto.comment.CommentResponseDto;
import kz.rssession.commons.dto.comment.CreateCommentRequestDto;
import kz.rssession.postservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentResponseDto createComment(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestBody CreateCommentRequestDto request
    ) {
        return commentService.createComment(userId, request);
    }

    @GetMapping("/post/{postId}")
    public List<CommentResponseDto> getPostComments(
            @PathVariable UUID postId
    ) {
        return commentService.getPostComments(postId);
    }
}