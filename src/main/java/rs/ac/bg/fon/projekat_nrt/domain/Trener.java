package rs.ac.bg.fon.projekat_nrt.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "trener")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Trener {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_trenera")
    private Integer idTrenera;

    @Column(name = "ime", nullable = false, length = 60)
    private String ime;

    @Column(name = "prezime", nullable = false, length = 60)
    private String prezime;

    @NotBlank(message = "Kontakt je obavezan.")
    @Pattern(
            regexp = "^06\\d{7,8}$",
            message = "Kontakt mora početi sa 06 i imati ukupno 9 ili 10 cifara (samo brojevi)."
    )
    @Column(name = "kontakt", nullable = false, length = 10)
    private String kontakt;

    @Column(name = "sertifikat")
    private Boolean sertifikat;

    @Column(name = "godine_iskustva")
    private Integer godineIskustva;

    private String putanjaDoFajla;
}

