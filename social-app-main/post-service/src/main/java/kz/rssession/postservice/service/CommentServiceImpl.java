package kz.rssession.postservice.service;

import jakarta.transaction.Transactional;
import kz.rssession.commons.dto.comment.CommentResponseDto;
import kz.rssession.commons.dto.comment.CreateCommentRequestDto;
import kz.rssession.postservice.entity.CommentEntity;
import kz.rssession.postservice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public CommentResponseDto createComment(UUID authorId, CreateCommentRequestDto request) {


        CommentEntity comment = CommentEntity.builder()
                .id(UUID.randomUUID())
                .postId(request.getPostId())
                .authorId(authorId)
                .content(request.getContent())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        commentRepository.save(comment);

        return map(comment);
    }

    @Override
    public List<CommentResponseDto> getPostComments(UUID postId) {

        return commentRepository.findByPostId(postId).stream()
                .map(this::map)
                .toList();
    }

    private CommentResponseDto map(CommentEntity entity) {
        return CommentResponseDto.builder()
                .id(entity.getId())
                .postId(entity.getPostId())
                .authorId(entity.getAuthorId())
                .content(entity.getContent())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}