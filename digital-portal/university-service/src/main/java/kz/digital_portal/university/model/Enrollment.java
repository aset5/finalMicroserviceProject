package kz.digital_portal.university.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "enrollments")
@Data
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long internshipId;
    private Long studentId;

    // Статус: STARTED, COMPLETED
    private String status;
}