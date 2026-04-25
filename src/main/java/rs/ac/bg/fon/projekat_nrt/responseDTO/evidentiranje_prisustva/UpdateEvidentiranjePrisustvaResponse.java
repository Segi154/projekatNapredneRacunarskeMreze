package rs.ac.bg.fon.projekat_nrt.responseDTO.evidentiranje_prisustva;

import lombok.*;
import rs.ac.bg.fon.projekat_nrt.responseDTO.korisnik.UpdateKorisnikResponse;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UpdateEvidentiranjePrisustvaResponse {

    private UpdateKorisnikResponse korisnik;
    private Integer potroseneKal;
    private Integer otkucajiSrca;
}
