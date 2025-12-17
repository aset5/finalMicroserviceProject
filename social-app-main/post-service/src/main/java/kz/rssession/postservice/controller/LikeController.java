package kz.rssession.postservice.controller;

import kz.rssession.commons.dto.like.LikeRequestDto;
import kz.rssession.postservice.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/likes")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void like(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestBody LikeRequestDto request
    ) {
        likeService.like(userId, request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void unlike(
            @RequestHeader("X-User-Id") UUID userId,
            @RequestBody LikeRequestDto request
    ) {
        likeService.unlike(userId, request);
    }
}