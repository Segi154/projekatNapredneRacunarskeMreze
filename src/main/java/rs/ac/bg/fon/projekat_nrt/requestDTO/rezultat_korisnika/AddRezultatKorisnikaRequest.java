package rs.ac.bg.fon.projekat_nrt.requestDTO.rezultat_korisnika;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AddRezultatKorisnikaRequest {

    @NotNull
    @Min(value = 0, message = "Procenat masti ne može biti negativan.")
    @Max(value = 100, message = "Procenat masti ne može biti veći od 100.")
    private Double procenatMasti;

    @NotNull
    @Min(value = 0, message = "Procenat mišića ne može biti negativan.")
    @Max(value = 100, message = "Procenat mišića ne može biti veći od 100.")
    private Double procenatMisica;

    @NotNull
    @Positive(message = "Težina mora biti pozitivna.")
    private Double tezinaUKG;

    @NotNull(message = "Datum rezultata je obavezan.")
    private LocalDate datumRezultata;
}
