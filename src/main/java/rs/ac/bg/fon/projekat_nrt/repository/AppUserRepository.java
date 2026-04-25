package rs.ac.bg.fon.projekat_nrt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.projekat_nrt.domain.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
    boolean existsByUsername(String username);
}
