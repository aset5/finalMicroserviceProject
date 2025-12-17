package kz.rssession.commons.client;

import kz.rssession.commons.dto.user.CreateUserRequestDto;
import kz.rssession.commons.dto.user.UpdateUserRequestDto;
import kz.rssession.commons.dto.user.UserContactDto;
import kz.rssession.commons.dto.user.UserResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(
        name = "user-service",
        path = "/api/users"
)

public interface UserClient {

    @PostMapping
    UserResponseDto createUser(@RequestBody CreateUserRequestDto request);

    @GetMapping("/{userId}")
    UserResponseDto getUserById(@PathVariable UUID userId);

    @GetMapping("/contact/{userId}")
    UserContactDto getUserContactById(@PathVariable UUID userId); //TODO

    @GetMapping
    List<UserResponseDto> getAllUsers();

    @PutMapping("/{userId}")
    UserResponseDto updateUser(@PathVariable UUID userId, @RequestBody UpdateUserRequestDto request);

    @DeleteMapping("/{userId}")
    void deleteUser(@PathVariable UUID userId);
}

