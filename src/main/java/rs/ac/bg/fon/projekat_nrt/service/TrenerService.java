package rs.ac.bg.fon.projekat_nrt.service;

import org.springframework.web.multipart.MultipartFile;
import rs.ac.bg.fon.projekat_nrt.requestDTO.trener.AddTrenerRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.trener.UpdateTrenerRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trener.AddTrenerResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trener.FindTrenerResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trener.UpdateTrenerResponse;

import java.util.List;

public interface TrenerService {

    AddTrenerResponse add(AddTrenerRequest req, MultipartFile file);

    UpdateTrenerResponse update(Integer id , UpdateTrenerRequest req);

    List<FindTrenerResponse> findAll(int page, int size);

    FindTrenerResponse findTrenerById(int id);
}
