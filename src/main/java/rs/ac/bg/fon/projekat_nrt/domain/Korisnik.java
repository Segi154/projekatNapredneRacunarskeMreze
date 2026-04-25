package rs.ac.bg.fon.projekat_nrt.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "korisnik")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Korisnik {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_korisnika")
    private Integer idKorisnika;

    @Column(name = "ime", nullable = false, length = 60)
    private String ime;

    @Column(name = "prezime", nullable = false, length = 60)
    private String prezime;

    @Column(name = "datum_rodjenja")
    private LocalDate datumRodjenja;

    @Column(name = "adresa", length = 200)
    private String adresa;

    @Column(name = "kontakt", length = 120)
    private String kontakt;

    @ManyToOne
    @JoinColumn(name = "id_grada")
    private Grad grad;

    @ManyToMany
    @JoinTable(
            name = "kt",
            joinColumns = @JoinColumn(name = "id_korisnika"),
            inverseJoinColumns = @JoinColumn(name = "id_tipa"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"id_korisnika", "id_tipa"})
    )
    @Builder.Default
    private List<TipTreninga> tipovi = new ArrayList<>();

    @OneToMany(mappedBy = "korisnik", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<RezultatKorisnika> rezultati = new ArrayList<>();
}


