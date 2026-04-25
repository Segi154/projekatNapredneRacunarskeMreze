package rs.ac.bg.fon.projekat_nrt.responseDTO.rezultat_korisnika;

import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AddRezultatKorisnikaResponse {

    private Integer korisnikId;
    private Integer idRez;

    private Double procenatMasti;
    private Double procenatMisica;
    private Double tezinaUKG;
    private LocalDate datumRezultata;
}
