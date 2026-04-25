package rs.ac.bg.fon.projekat_nrt.responseDTO.evidentiranje_prisustva;

import lombok.*;
import rs.ac.bg.fon.projekat_nrt.responseDTO.korisnik.AddKorisnikResponse;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AddEvidentiranjePrisustvaResponse {

    private AddKorisnikResponse korisnik;
    private Integer potroseneKal;
    private Integer otkucajiSrca;
}
