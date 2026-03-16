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


}