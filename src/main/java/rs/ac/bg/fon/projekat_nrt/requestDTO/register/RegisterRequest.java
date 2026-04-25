package rs.ac.bg.fon.projekat_nrt.requestDTO.register;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class RegisterRequest {
    @NotBlank private String username;
    @NotBlank private String password;
}
