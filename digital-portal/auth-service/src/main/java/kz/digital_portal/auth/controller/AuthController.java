package kz.digital_portal.auth.controller;

import kz.digital_portal.auth.dto.*;
import kz.digital_portal.auth.model.User;
import kz.digital_portal.auth.model.Role;
import kz.digital_portal.auth.repository.UserRepository;
import kz.digital_portal.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthService authService;

    // ИСПОЛЬЗУЕМ СЕРВИС ДЛЯ ЛОГИНА
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.status(401).body(e.getMessage());
        }
    }

    @GetMapping("/universities")
    public List<UserResponseDTO> getUniversities() {
        return userRepository.findAllByRole(Role.UNIVERSITY)
                .stream()
                .map(user -> new UserResponseDTO(user.getId(), user.getFullName()))
                .toList();
    }

    @PutMapping("/users/{id}/update")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        return userRepository.findById(id).map(user -> {
            user.setFullName(request.getFullName());
            user.setRole(request.getRole());
            user.setEnabled(request.isEnabled()); // Применяем статус блокировки

            userRepository.save(user);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email уже занят!");
        }

        User user = new User();
        user.setEmail(request.getEmail());

        user.setPassword(authService.encodePassword(request.getPassword()));

        user.setFullName(request.getFullName());
        user.setRole(request.getRole());

        if (request.getRole() == Role.STUDENT) {
            if (request.getUniversityId() == null) {
                return ResponseEntity.badRequest().body("Студент должен выбрать университет!");
            }
            if (!userRepository.existsById(request.getUniversityId())) {
                return ResponseEntity.badRequest().body("Выбранный университет не найден!");
            }
            user.setUniversityId(request.getUniversityId());
        }

        userRepository.save(user);
        return ResponseEntity.ok("Пользователь успешно зарегистрирован как " + request.getRole());
    }

    @GetMapping("/users/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userRepository.findAll());
    }


}