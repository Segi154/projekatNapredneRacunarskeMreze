package rs.ac.bg.fon.projekat_nrt.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "trening")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Trening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_treninga")
    private Integer idTreninga;

    @Column(name = "cena", nullable = false)
    private Integer cena;

    @Column(name = "datum_treninga", nullable = false)
    private LocalDateTime datumTreninga;

    @Column(name = "trajanje_u_min", nullable = false)
    private Integer trajanjeUMin;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "id_tipa", nullable = false)
    private TipTreninga tip;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "id_trenera", nullable = false)
    private Trener trener;
}

