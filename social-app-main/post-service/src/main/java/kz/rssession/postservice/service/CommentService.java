package kz.rssession.postservice.service;

import kz.rssession.commons.dto.comment.CommentResponseDto;
import kz.rssession.commons.dto.comment.CreateCommentRequestDto;

import java.util.List;
import java.util.UUID;

public interface CommentService {

    CommentResponseDto createComment(UUID authorId, CreateCommentRequestDto request);

    List<CommentResponseDto> getPostComments(UUID postId);
}