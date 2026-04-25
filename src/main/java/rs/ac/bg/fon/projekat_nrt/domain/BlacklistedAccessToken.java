package rs.ac.bg.fon.projekat_nrt.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(
        name = "blacklisted_access_token",
        indexes = {
                @Index(name = "idx_blacklisted_jti", columnList = "jti", unique = true),
                @Index(name = "idx_blacklisted_exp", columnList = "expiresAt")
        }
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class BlacklistedAccessToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 120)
    private String jti;

    @Column(nullable = false)
    private Instant expiresAt;
}
