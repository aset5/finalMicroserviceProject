package kz.digital_portal.auth.service;

import kz.digital_portal.auth.dto.LoginRequest;
import kz.digital_portal.auth.dto.AuthResponse;
import kz.digital_portal.auth.model.User;
import kz.digital_portal.auth.repository.UserRepository;
import kz.digital_portal.auth.util.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));

        // ПРОВЕРКА БЛОКИРОВКИ
        if (!user.isEnabled()) {
            throw new RuntimeException("Ваш аккаунт заблокирован. Обратитесь к администратору.");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Неверный email или пароль");
        }

        String token = jwtUtil.generateToken(user);
        return new AuthResponse(token);
    }

    public String encodePassword(String rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }
}