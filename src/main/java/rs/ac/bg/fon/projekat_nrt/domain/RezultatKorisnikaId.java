package rs.ac.bg.fon.projekat_nrt.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class RezultatKorisnikaId implements Serializable {

    @Column(name = "id_korisnika")
    private Integer korisnikId;

    @Column(name = "id_rez")
    private Integer idRez;
}

