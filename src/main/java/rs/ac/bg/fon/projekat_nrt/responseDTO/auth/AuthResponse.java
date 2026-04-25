package rs.ac.bg.fon.projekat_nrt.responseDTO.auth;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
    private String role;
}
