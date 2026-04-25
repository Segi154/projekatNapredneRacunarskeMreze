package rs.ac.bg.fon.projekat_nrt.requestDTO.grad;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GradRequest {

    @NotBlank(message = "Naziv grada je obavezan.")
    private String naziv;
}
