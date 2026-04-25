package rs.ac.bg.fon.projekat_nrt.service;

import rs.ac.bg.fon.projekat_nrt.requestDTO.korisnik.AddKorisnikRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.rezultat_korisnika.AddRezultatKorisnikaRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.korisnik.UpdateKorisnikRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.korisnik.AddKorisnikResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.korisnik.FindKorisnikResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.korisnik.UpdateKorisnikResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.rezultat_korisnika.AddRezultatKorisnikaResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.rezultat_korisnika.FindRezultatKorisnikaResponse;

import java.util.List;

public interface KorisnikService {

    AddKorisnikResponse add(AddKorisnikRequest req);

    UpdateKorisnikResponse update(Integer id, UpdateKorisnikRequest req);

    List<FindKorisnikResponse> findAll(int page, int size);

    FindKorisnikResponse findById(int id);

    String delete(Integer id);

    AddRezultatKorisnikaResponse addRezultat(int korisnikId, AddRezultatKorisnikaRequest req);

    List<FindRezultatKorisnikaResponse> getRezultati(int korisnikId, int page, int size);
}
