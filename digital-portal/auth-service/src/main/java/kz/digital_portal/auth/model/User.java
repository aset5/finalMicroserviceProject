package kz.digital_portal.auth.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    private String fullName;

    @Enumerated(EnumType.STRING)
    private Role role;

    // ПЕРЕМЕННАЯ ДОЛЖНА БЫТЬ ОБЪЯВЛЕНА ЗДЕСЬ
    @Column(nullable = false)
    private boolean enabled = true;

    // Добавь это в User.java
    @Column(name = "university_id")
    private Long universityId;

    // Если ты НЕ используешь @Data от Lombok,
    // добавь эти методы вручную:

    /*
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    */
}