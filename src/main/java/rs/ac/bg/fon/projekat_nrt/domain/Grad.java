package rs.ac.bg.fon.projekat_nrt.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "grad")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Grad {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grada")
    private Integer idGrada;

    @Column(name = "naziv", nullable = false, length = 120)
    private String naziv;
}

