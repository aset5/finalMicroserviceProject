package kz.digital_portal.university.repository;

import kz.digital_portal.university.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    Optional<Enrollment> findByStudentId(Long studentId);

    List<Enrollment> findAllByInternshipId(Long internshipId);
}