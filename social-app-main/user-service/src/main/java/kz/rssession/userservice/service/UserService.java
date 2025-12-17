package kz.rssession.userservice.service;

import kz.rssession.commons.dto.user.CreateUserRequestDto;
import kz.rssession.commons.dto.user.UpdateUserRequestDto;
import kz.rssession.commons.dto.user.UserResponseDto;

import java.util.List;
import java.util.UUID;

public interface UserService {

    UserResponseDto createUser(CreateUserRequestDto request);

    UserResponseDto getUserById(UUID userId);

    List<UserResponseDto> getAllUsers();

    UserResponseDto updateUser(UUID userId, UpdateUserRequestDto request);

    void deleteUser(UUID userId);
}

