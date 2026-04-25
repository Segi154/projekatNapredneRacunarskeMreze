package rs.ac.bg.fon.projekat_nrt.service;

import rs.ac.bg.fon.projekat_nrt.requestDTO.evidentiranje_prisustva.AddEvidentiranjePrisustvaRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.evidentiranje_prisustva.UpdateEvidentiranjePrisustvaRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.evidentiranje_prisustva.AddComprehensiveEPResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.evidentiranje_prisustva.FindEvidentiranjePrisustvaResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.evidentiranje_prisustva.UpdateComprehensiveEPResponse;

import java.util.List;

public interface EvidentiranjePrisustvaService {

    AddComprehensiveEPResponse add(List<AddEvidentiranjePrisustvaRequest> req);

    UpdateComprehensiveEPResponse update(List<UpdateEvidentiranjePrisustvaRequest> req);

    List<FindEvidentiranjePrisustvaResponse> findAll(int page, int size);

    FindEvidentiranjePrisustvaResponse findById(int treningId, int korisnikId);

    String delete(int treningId, int korisnikId);
}
