package rs.ac.bg.fon.projekat_nrt.requestDTO.refresh;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class RefreshRequest {

    @NotBlank
    private String refreshToken;
}
