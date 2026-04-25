package rs.ac.bg.fon.projekat_nrt.responseDTO.evidentiranje_prisustva;

import lombok.*;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trening.UpdateTreningResponse;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateComprehensiveEPResponse {

    private UpdateTreningResponse trening;
    private List<UpdateEvidentiranjePrisustvaResponse> evidentiranjePrisustvaResponse;
}
