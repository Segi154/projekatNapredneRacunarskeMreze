package rs.ac.bg.fon.projekat_nrt.requestDTO.trening;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.FutureOrPresent;
import lombok.*;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AddTreningRequest {

    @NotNull(message = "Cena je obavezna.")
    @Min(value = 0, message = "Cena ne može biti negativna.")
    private Integer cena;

    @NotNull(message = "Datum treninga je obavezana.")
    @FutureOrPresent(message = "Datum treninga ne može biti u prošlosti.")
    private LocalDateTime datumTreninga;

    @NotNull(message = "Trajanje u minutima je obavezno.")
    @Min(value = 1, message = "Trajanje mora biti bar 1 minut.")
    private Integer trajanjeUMin;

    @NotNull(message = "tipId je obavezan.")
    @Min(value = 1, message = "tipId mora biti >= 1.")
    private Integer tipId;

    @NotNull(message = "trenerId je obavezan.")
    @Min(value = 1, message = "trenerId mora biti >= 1.")
    private Integer trenerId;
}

