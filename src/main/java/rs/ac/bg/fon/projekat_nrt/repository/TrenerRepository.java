package rs.ac.bg.fon.projekat_nrt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.bg.fon.projekat_nrt.domain.Trener;

public interface TrenerRepository extends JpaRepository<Trener, Integer> {

    Page<Trener> findByImeContainingIgnoreCaseAndPrezimeContainingIgnoreCase(
            String ime, String prezime, Pageable pageable
    );

    Page<Trener> findByImeContainingIgnoreCaseAndPrezimeContainingIgnoreCaseAndSertifikat(
            String ime, String prezime, Boolean sertifikat, Pageable pageable
    );


}

