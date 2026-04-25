package rs.ac.bg.fon.projekat_nrt.responseDTO.korisnik;

import lombok.*;
import rs.ac.bg.fon.projekat_nrt.responseDTO.grad.GradResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.tip_treninga.TipTreningaResponse;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AddKorisnikResponse {

    private Integer idKorisnika;
    private String ime;
    private String prezime;
    private LocalDate datumRodjenja;
    private String adresa;
    private String kontakt;
    private GradResponse grad;
    private List<TipTreningaResponse> tipovi;
}
