package rs.ac.bg.fon.projekat_nrt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.projekat_nrt.domain.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByTokenHashAndRevokedFalse(String tokenHash);

    long deleteByUser_Id(Long userId);
}