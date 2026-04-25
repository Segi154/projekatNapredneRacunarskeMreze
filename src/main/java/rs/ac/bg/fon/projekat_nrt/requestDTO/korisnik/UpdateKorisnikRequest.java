package rs.ac.bg.fon.projekat_nrt.requestDTO.korisnik;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UpdateKorisnikRequest {

    @NotBlank(message = "Ime je obavezno.")
    private String ime;

    @NotBlank(message = "Prezime je obavezno.")
    private String prezime;

    @NotNull(message = "Datum rođenja je obavezan.")
    @Past(message = "Datum rođenja mora biti u prošlosti.")
    private LocalDate datumRodjenja;

    @NotBlank(message = "Adresa je obavezna.")
    private String adresa;

    @NotBlank(message = "Kontakt je obavezan.")
    @Pattern(
            regexp = "^06\\d{7,8}$",
            message = "Kontakt mora početi sa 06 i imati ukupno 9 ili 10 cifara (samo brojevi)."
    )
    private String kontakt;

    @NotNull(message = "gradId je obavezan.")
    @Min(value = 1, message = "gradId mora biti >= 1.")
    private Integer gradId;

    @NotNull(message = "tipoviIds su obavezni.")
    @Size(min = 1, message = "Moraš izabrati bar jedan tip treninga.")
    private List<@NotNull @Min(value = 1, message = "Svaki tipId mora biti >= 1.") Integer> tipoviIds;
}
