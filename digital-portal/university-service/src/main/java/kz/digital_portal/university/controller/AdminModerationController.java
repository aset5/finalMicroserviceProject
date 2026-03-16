package kz.digital_portal.university.controller;

import kz.digital_portal.university.model.Internship;
import kz.digital_portal.university.repository.InternshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminModerationController {

    private final InternshipRepository internshipRepository;

    // Список всех стажировок, которые ждут проверки
    @GetMapping("/moderation/pending")
    public List<Internship> getPendingInternships() {
        return internshipRepository.findAllByStatus(Internship.InternshipStatus.PENDING);
    }

    // Одобрить стажировку
    @PostMapping("/internship/{id}/approve")
    public ResponseEntity<?> approve(@PathVariable Long id) {
        return internshipRepository.findById(id).map(i -> {
            i.setStatus(Internship.InternshipStatus.APPROVED);
            internshipRepository.save(i);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}