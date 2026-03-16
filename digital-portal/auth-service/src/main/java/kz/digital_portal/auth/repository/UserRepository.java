package kz.digital_portal.auth.repository;


import kz.digital_portal.auth.model.User;
import kz.digital_portal.auth.model.Role;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findAllByRole(Role role);

    Optional<User> findByEmail(String email);

    boolean existsById(Long id);

}