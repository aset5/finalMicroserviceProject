package kz.digital_portal.university.repository;

import kz.digital_portal.university.model.Internship;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InternshipRepository extends JpaRepository<Internship, Long> {
    List<Internship> findAllByUniversityId(Long universityId);

    // НОВОЕ: Поиск всех программ по статусу (нужно для админа)
    List<Internship> findAllByStatus(Internship.InternshipStatus status);
}
