package rs.ac.bg.fon.projekat_nrt.responseDTO.trening;

import lombok.*;
import rs.ac.bg.fon.projekat_nrt.responseDTO.tip_treninga.TipTreningaResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trener.UpdateTrenerResponse;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UpdateTreningResponse {

    private Integer idTreninga;
    private Integer cena;
    private LocalDateTime datumTreninga;
    private Integer trajanjeUMin;

    private UpdateTrenerResponse trener;
    private TipTreningaResponse tip;
}
