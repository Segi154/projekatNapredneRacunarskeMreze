package rs.ac.bg.fon.projekat_nrt.responseDTO.evidentiranje_prisustva;

import lombok.*;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trening.AddTreningResponse;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddComprehensiveEPResponse {

    private AddTreningResponse trening;
    private List<AddEvidentiranjePrisustvaResponse> evidentiranjePrisustvaResponse;

}
