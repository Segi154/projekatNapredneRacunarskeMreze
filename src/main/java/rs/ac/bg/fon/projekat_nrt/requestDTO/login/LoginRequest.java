package rs.ac.bg.fon.projekat_nrt.requestDTO.login;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class LoginRequest {
    @NotBlank private String username;
    @NotBlank private String password;
}
