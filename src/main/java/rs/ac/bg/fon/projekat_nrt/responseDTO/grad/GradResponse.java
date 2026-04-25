package rs.ac.bg.fon.projekat_nrt.responseDTO.grad;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GradResponse {
    private Integer idGrada;
    private String naziv;
}
