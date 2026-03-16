package kz.digital_portal.university.controller;

import kz.digital_portal.university.model.Enrollment;
import kz.digital_portal.university.model.Internship;
import kz.digital_portal.university.repository.EnrollmentRepository;
import kz.digital_portal.university.repository.InternshipRepository;
import kz.digital_portal.university.service.InternshipService; // Проверь этот импорт
import lombok.RequiredArgsConstructor; // Удобно для конструктора
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/university")
// Заменяем @Autowired на конструктор через Lombok для всех final полей
@RequiredArgsConstructor
public class UniversityController {

    private final InternshipRepository internshipRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final InternshipService internshipService; // Теперь инициализируется корректно

    // 1. Создание стажировки (JSON версия)
    @PostMapping("/internship/create")
    public ResponseEntity<?> createInternshipApi(@RequestBody Internship internship) {
        internship.setAvailableSlots(internship.getMaxSlots());
        internship.setVisibleToCompanies(false);
        // Устанавливаем статус PENDING, чтобы админ сразу её увидел
        internship.setStatus(Internship.InternshipStatus.PENDING);
        internshipRepository.save(internship);
        return ResponseEntity.ok("Стажировка создана и отправлена на модерацию");
    }

    // 2. Версия для HTML-формы (с кнопками)
    @PostMapping("/create")
    public ResponseEntity<?> createInternshipForm(@ModelAttribute Internship internship,
                                                  @RequestParam(required = false) String action) {
        if ("submit".equals(action)) {
            internship.setStatus(Internship.InternshipStatus.PENDING); // К админу
        } else {
            internship.setStatus(Internship.InternshipStatus.DRAFT);   // В черновик
        }

        internshipService.save(internship);
        // Так как это @RestController, лучше вернуть статус,
        // но если нужен редирект на фронте, используй:
        return ResponseEntity.ok("Статус установлен: " + internship.getStatus());
    }

    @GetMapping("/internships-by-university/{uniId}")
    public List<Internship> getUniInternships(@PathVariable Long uniId) {
        return internshipRepository.findAllByUniversityId(uniId);
    }

    @PostMapping("/complete-internship")
    public String complete(@RequestParam Long studentId) {
        Enrollment enrollment = enrollmentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Запись не найдена"));

        enrollment.setStatus("COMPLETED");
        enrollmentRepository.save(enrollment);
        return "Стажировка успешно завершена!";
    }

    @GetMapping("/student/{studentId}/is-ready")
    public boolean checkStudentStatus(@PathVariable Long studentId) {
        return enrollmentRepository.findByStudentId(studentId)
                .map(enrollment -> "COMPLETED".equals(enrollment.getStatus()))
                .orElse(false);
    }
}