package kz.digital_portal.university.service;

import kz.digital_portal.university.model.Internship;
import kz.digital_portal.university.repository.InternshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InternshipService {

    private final InternshipRepository internshipRepository;

    public void save(Internship internship) {
        internshipRepository.save(internship);
    }
}