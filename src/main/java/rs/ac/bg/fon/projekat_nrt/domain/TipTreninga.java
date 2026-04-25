package rs.ac.bg.fon.projekat_nrt.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tip_treninga")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TipTreninga {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipa")
    private Integer idTipa;

    @Column(name = "naziv_tipa", nullable = false, length = 100)
    private String nazivTipa;

    @Column(name = "opis", length = 500)
    private String opis;
}
