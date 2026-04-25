package rs.ac.bg.fon.projekat_nrt.requestDTO.trener;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AddTrenerRequest {

    @NotBlank
    private String ime;

    @NotBlank
    private String prezime;

    @NotBlank
    @Pattern(
            regexp = "^06\\d{7,8}$",
            message = "Kontakt mora početi sa 06 i imati ukupno 9 ili 10 cifara (samo brojevi)."
    )
    private String kontakt;

    @NotNull
    private Boolean sertifikat;

    private Integer godineIskustva;
}
