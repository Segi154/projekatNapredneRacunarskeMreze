package rs.ac.bg.fon.projekat_nrt.responseDTO.evidentiranje_prisustva;

import lombok.*;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trening.AddTreningResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.korisnik.FindKorisnikResponse;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class FindEvidentiranjePrisustvaResponse {

    private FindKorisnikResponse korisnik;
    private AddTreningResponse trening;

    private Integer potroseneKal;
    private Integer otkucajiSrca;
}
