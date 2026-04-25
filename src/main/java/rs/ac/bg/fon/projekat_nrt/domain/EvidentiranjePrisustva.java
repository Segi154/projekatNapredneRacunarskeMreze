package rs.ac.bg.fon.projekat_nrt.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "evidentiranje_prisustva")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class EvidentiranjePrisustva {

    @EmbeddedId
    private EvidentiranjePrisustvaId id;

    @ManyToOne
    @MapsId("treningId")
    @JoinColumn(name = "id_treninga", nullable = false)
    private Trening trening;

    @ManyToOne
    @MapsId("korisnikId")
    @JoinColumn(name = "id_korisnika", nullable = false)
    private Korisnik korisnik;

    @Column(name = "potrosene_kal")
    private Integer potroseneKal;

    @Column(name = "otkucaji_srca")
    private Integer otkucajiSrca;
}

