package rs.ac.bg.fon.projekat_nrt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.projekat_nrt.domain.BlacklistedAccessToken;

import java.time.Instant;
import java.util.List;

public interface BlacklistedAccessTokenRepository extends JpaRepository<BlacklistedAccessToken, Long> {

    boolean existsByJti(String jti);

    long deleteByExpiresAtBefore(Instant now);

    List<BlacklistedAccessToken> findTop1000ByExpiresAtBefore(Instant now);

}
