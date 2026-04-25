package rs.ac.bg.fon.projekat_nrt.responseDTO.tip_treninga;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class TipTreningaResponse {
    private Integer idTipa;
    private String nazivTipa;
    private String opis;
}
