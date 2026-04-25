package rs.ac.bg.fon.projekat_nrt.requestDTO.evidentiranje_prisustva;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UpdateEvidentiranjePrisustvaRequest {

    @NotNull(message = "korisnikId je obavezan.")
    @Min(value = 1, message = "korisnikId mora biti >= 1.")
    private Integer korisnikId;

    @NotNull(message = "treningId je obavezan.")
    @Min(value = 1, message = "treningId mora biti >= 1.")
    private Integer treningId;

    @NotNull(message = "Potrosene kalorije su obavezne.")
    @Min(value = 0, message = "Potrosene kalorije ne mogu biti negativne.")
    private Integer potroseneKal;

    @NotNull(message = "Otkucaji srca su obavezni.")
    @Min(value = 0, message = "Otkucaji srca ne mogu biti negativni.")
    private Integer otkucajiSrca;
}
