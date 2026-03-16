package kz.digital_portal.auth.service;

import kz.digital_portal.auth.model.Role; // УБЕДИСЬ, ЧТО ИМПОРТ ТАКОЙ

import kz.digital_portal.auth.model.User; // УБЕДИСЬ, ЧТО ИМПОРТ ТАКОЙ
import kz.digital_portal.auth.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        String targetEmail = "admin@gmail.com"; // Почта, которую ты хочешь использовать

        if (userRepository.findByEmail(targetEmail).isEmpty()) {
            User admin = new User();
            admin.setEmail(targetEmail);
            admin.setPassword(passwordEncoder.encode("1234"));
            admin.setFullName("System Administrator");
            admin.setRole(Role.ADMIN);

            userRepository.save(admin);
            System.out.println("✅ [Auth-Service] Администратор создан: " + targetEmail);
        } else {
            System.out.println("ℹ️ [Auth-Service] Пользователь " + targetEmail + " уже существует.");
        }
    }
}