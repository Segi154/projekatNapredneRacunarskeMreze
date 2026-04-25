package rs.ac.bg.fon.projekat_nrt.service;

import rs.ac.bg.fon.projekat_nrt.requestDTO.trening.AddTreningRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.trening.UpdateTreningRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trening.AddTreningResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trening.FindTreningResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trening.UpdateTreningResponse;

import java.util.List;


public interface TreningService {
    AddTreningResponse add(AddTreningRequest req);

    UpdateTreningResponse update(Integer id, UpdateTreningRequest req);
    List<FindTreningResponse> findAll(int page, int size);

    void delete(Integer id);

}
