package rs.ac.bg.fon.projekat_nrt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.projekat_nrt.domain.EvidentiranjePrisustva;
import rs.ac.bg.fon.projekat_nrt.domain.EvidentiranjePrisustvaId;

public interface EvidentiranjePrisustvaRepository extends JpaRepository<EvidentiranjePrisustva, EvidentiranjePrisustvaId> {
}
