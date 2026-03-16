package kz.digital_portal.gateway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class WebController {
    @GetMapping("/")
    public Mono<String> index() {
        // Это перенаправит пользователя на логин или дашборд при заходе на localhost:8080
        return Mono.just("login");
    }
    @GetMapping("/admin/panel")
    public Mono<String> adminPanel() {
        return Mono.just("admin_panel");
    }

    @GetMapping("/student/dashboard")
    public Mono<String> studentDashboard() {
        return Mono.just("student_dashboard");
    }

    @GetMapping("/university/dashboard")
    public Mono<String> uniDashboard() {
        return Mono.just("uni_dashboard");
    }

    @GetMapping("/company/dashboard")
    public Mono<String> companyDashboard() {
        return Mono.just("company_dashboard");
    }
    @GetMapping("/register")
    public Mono<String> registerPage() {
        // Убедись, что файл в templates называется именно registration.html
        // или поменяй здесь на то имя, которое у тебя у файла регистрации
        return Mono.just("register");
    }

    @GetMapping("/login")
    public Mono<String> loginPage() {
        // Убедись, что файл в templates называется именно registration.html
        // или поменяй здесь на то имя, которое у тебя у файла регистрации
        return Mono.just("login");
    }
}