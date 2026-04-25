package rs.ac.bg.fon.projekat_nrt.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class EvidentiranjePrisustvaId implements Serializable {

    @Column(name = "id_treninga")
    private Integer treningId;

    @Column(name = "id_korisnika")
    private Integer korisnikId;
}

