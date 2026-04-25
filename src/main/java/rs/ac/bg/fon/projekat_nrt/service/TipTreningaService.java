package rs.ac.bg.fon.projekat_nrt.service;

import rs.ac.bg.fon.projekat_nrt.requestDTO.tip_treninga.TipTreningaRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.tip_treninga.TipTreningaResponse;

public interface TipTreningaService {

    TipTreningaResponse findById(int id);

    TipTreningaResponse addTip(TipTreningaRequest req);
}
