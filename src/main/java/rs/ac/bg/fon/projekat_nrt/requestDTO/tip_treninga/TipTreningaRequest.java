package rs.ac.bg.fon.projekat_nrt.requestDTO.tip_treninga;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TipTreningaRequest {

    @NotBlank(message = "Naziv tipa je obavezan.")
    private String nazivTipa;

    private String opis;
}

