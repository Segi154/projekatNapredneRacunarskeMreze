package rs.ac.bg.fon.projekat_nrt.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "rezultat_korisnika")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class RezultatKorisnika {

    @EmbeddedId
    private RezultatKorisnikaId id;

    @ManyToOne
    @MapsId("korisnikId")
    @JoinColumn(name = "id_korisnika", nullable = false)
    private Korisnik korisnik;

    @Column(name = "procenat_masti")
    private Double procenatMasti;

    @Column(name = "procenat_misica")
    private Double procenatMisica;

    @Column(name = "tezina_u_kg")
    private Double tezinaUKG;

    @Column(name = "datum_rezultata")
    private LocalDate datumRezultata;
}

