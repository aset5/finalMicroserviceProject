package kz.digital_portal.university.service;

import kz.digital_portal.university.model.Internship;
import kz.digital_portal.university.repository.InternshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor // Автоматически создаст конструктор для всех final полей
public class UniversityService {

    // 1. Обязательно добавь это поле с пометкой final
    private final InternshipRepository internshipRepository;

    public Internship createInternship(Internship internship) {
        internship.setAvailableSlots(internship.getMaxSlots());
        internship.setVisibleToCompanies(false);
        // Теперь компилятор найдет эту переменную:
        return internshipRepository.save(internship);
    }

    public List<Internship> getMyInternships(Long universityId) {
        return internshipRepository.findAllByUniversityId(universityId);
    }

    public void publishInternship(Long id) {
        internshipRepository.findById(id).ifPresent(i -> {
            i.setVisibleToCompanies(true);
            internshipRepository.save(i);
        });
    }
    public void sendToModeration(Long id) {
        internshipRepository.findById(id).ifPresent(i -> {
            i.setStatus(Internship.InternshipStatus.PENDING);
            internshipRepository.save(i);
        });
    }
}