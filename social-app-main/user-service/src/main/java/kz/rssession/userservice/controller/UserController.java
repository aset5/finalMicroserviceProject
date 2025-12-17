package kz.rssession.userservice.controller;

import kz.rssession.commons.dto.user.CreateUserRequestDto;
import kz.rssession.commons.dto.user.UpdateUserRequestDto;
import kz.rssession.commons.dto.user.UserResponseDto;
import kz.rssession.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(@RequestBody CreateUserRequestDto request) {
        return userService.createUser(request);
    }

    @GetMapping("/{userId}")
    public UserResponseDto getUserById(@PathVariable UUID userId) {
        return userService.getUserById(userId);
    }

    @GetMapping
    public List<UserResponseDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @PutMapping("/{userId}")
    public UserResponseDto updateUser(@PathVariable UUID userId, @RequestBody UpdateUserRequestDto request) {
        return userService.updateUser(userId, request);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable UUID userId) {
        userService.deleteUser(userId);
    }
}

