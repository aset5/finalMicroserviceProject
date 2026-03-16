package kz.digital_portal.university.dto;

import lombok.Data;

@Data
public class InternshipRequest {
    private String title;
    private String description;
    private Long universityId;
}