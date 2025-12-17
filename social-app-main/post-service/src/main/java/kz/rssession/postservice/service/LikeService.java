package kz.rssession.postservice.service;


import kz.rssession.commons.dto.like.LikeRequestDto;

import java.util.UUID;

public interface LikeService {

    void like(UUID userId, LikeRequestDto request);

    void unlike(UUID userId, LikeRequestDto request);
}