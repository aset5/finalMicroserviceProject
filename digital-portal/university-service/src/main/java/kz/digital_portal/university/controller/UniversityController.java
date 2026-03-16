package kz.digital_portal.university.controller;

import kz.digital_portal.university.model.Enrollment;
import kz.digital_portal.university.model.Internship;
import kz.digital_portal.university.repository.EnrollmentRepository;
import kz.digital_portal.university.repository.InternshipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/university")
public class UniversityController {

    @Autowired
    private InternshipRepository internshipRepository;

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    // Студент видит список стажировок своего ВУЗа
    @GetMapping("/internships-by-university/{uniId}")
    public List<Internship> getUniInternships(@PathVariable Long uniId) {
        return internshipRepository.findAllByUniversityId(uniId);
    }

    // Университет завершает стажировку студента
    @PostMapping("/complete-internship")
    public String complete(@RequestParam Long studentId) {
        Enrollment enrollment = enrollmentRepository.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Запись не найдена"));

        enrollment.setStatus("COMPLETED");
        enrollmentRepository.save(enrollment);
        return "Стажировка успешно завершена!";
    }

    // ПРОВЕРКА ДЛЯ COMPANY SERVICE
    // Этот метод — "мост". Когда студент захочет зайти в Company Service,
    // тот вызовет этот метод.
    @GetMapping("/student/{studentId}/is-ready")
    public boolean checkStudentStatus(@PathVariable Long studentId) {
        return enrollmentRepository.findByStudentId(studentId)
                .map(enrollment -> "COMPLETED".equals(enrollment.getStatus()))
                .orElse(false);
    }

    @PostMapping("/internship/create")
    public ResponseEntity<?> createInternship(@RequestBody Internship internship) {
        internship.setAvailableSlots(internship.getMaxSlots());
        internship.setVisibleToCompanies(false); // По умолчанию скрыта
        internshipRepository.save(internship);
        return ResponseEntity.ok("Стажировка создана и ждет публикации");
    }

    // 2. Дать доступ компаниям к этой вакансии
    @PostMapping("/internship/{id}/publish")
    public ResponseEntity<?> publishToCompanies(@PathVariable Long id) {
        return internshipRepository.findById(id)
                .map(internship -> {
                    internship.setVisibleToCompanies(true);
                    internshipRepository.save(internship);
                    return ResponseEntity.ok("Вакансия теперь доступна компаниям");
                })
                .orElse(ResponseEntity.notFound().build());
    }
}