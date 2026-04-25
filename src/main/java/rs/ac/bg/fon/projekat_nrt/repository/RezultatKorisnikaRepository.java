package rs.ac.bg.fon.projekat_nrt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import rs.ac.bg.fon.projekat_nrt.domain.RezultatKorisnika;
import rs.ac.bg.fon.projekat_nrt.domain.RezultatKorisnikaId;

import java.util.List;

public interface RezultatKorisnikaRepository extends JpaRepository<RezultatKorisnika, RezultatKorisnikaId> {

    Page<RezultatKorisnika> findByKorisnik_IdKorisnikaOrderByDatumRezultataDesc(Integer korisnikId, Pageable pageable);

    @Query("select coalesce(max(r.id.idRez), 0) from RezultatKorisnika r where r.korisnik.idKorisnika = :korisnikId")
    Integer maxIdRezForKorisnik(@Param("korisnikId") Integer korisnikId);
}
