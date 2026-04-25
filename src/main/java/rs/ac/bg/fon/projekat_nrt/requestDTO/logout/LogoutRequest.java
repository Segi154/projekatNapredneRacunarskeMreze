package rs.ac.bg.fon.projekat_nrt.requestDTO.logout;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class LogoutRequest {

    @NotBlank
    private String refreshToken;
    private String accessToken;
}

