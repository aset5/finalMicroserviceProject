package kz.digital_portal.university.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "internships")
@Data
public class Internship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;

    // ID университета из Auth Service
    private Long universityId;

    private int maxSlots; // Ограниченные места
    private int availableSlots;

    private boolean isVisibleToCompanies = false; // Доступ для компаний

    public enum InternshipStatus {
        DRAFT,      // Только что создана вузом
        PENDING,    // Отправлена админу на проверку
        APPROVED,   // Одобрена админом
        REJECTED,   // Отклонена админом
        PUBLISHED   // Открыта для компаний (финальный этап)
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private InternshipStatus status = InternshipStatus.DRAFT;
}