package rs.ac.bg.fon.projekat_nrt.responseDTO.trener;

import lombok.*;

@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class UpdateTrenerResponse {
    private Integer idTrenera;
    private String ime;
    private String prezime;
    private String kontakt;
    private Boolean sertifikat;
    private Integer godineIskustva;
}

