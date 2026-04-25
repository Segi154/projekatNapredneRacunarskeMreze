package rs.ac.bg.fon.projekat_nrt.service.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.projekat_nrt.domain.BlacklistedAccessToken;
import rs.ac.bg.fon.projekat_nrt.repository.BlacklistedAccessTokenRepository;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class AccessTokenBlacklistService {

    private final BlacklistedAccessTokenRepository repo;

    public boolean isBlacklisted(String jti) {
        return repo.existsByJti(jti);
    }

    @Transactional
    public void blacklist(String jti, Instant expiresAt) {
        if (jti == null || expiresAt == null) return;
        if (repo.existsByJti(jti)) return;

        repo.save(BlacklistedAccessToken.builder()
                .jti(jti)
                .expiresAt(expiresAt)
                .build());
    }

    @Transactional
    public void cleanupExpired() {
        repo.deleteByExpiresAtBefore(Instant.now());
    }

    @Transactional
    public int cleanupExpiredWithLogging() {
        var expired = repo.findTop1000ByExpiresAtBefore(Instant.now());
        if (expired.isEmpty()) return 0;

        for (var e : expired) {
            System.out.println("Blacklist cleanup: brisem jti=" + e.getJti() + " exp=" + e.getExpiresAt());
        }

        repo.deleteAll(expired);
        return expired.size();
    }
}
