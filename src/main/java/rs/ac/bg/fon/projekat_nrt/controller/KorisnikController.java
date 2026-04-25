package rs.ac.bg.fon.projekat_nrt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.projekat_nrt.requestDTO.korisnik.AddKorisnikRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.rezultat_korisnika.AddRezultatKorisnikaRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.korisnik.UpdateKorisnikRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.korisnik.AddKorisnikResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.korisnik.FindKorisnikResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.korisnik.UpdateKorisnikResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.rezultat_korisnika.AddRezultatKorisnikaResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.rezultat_korisnika.FindRezultatKorisnikaResponse;
import rs.ac.bg.fon.projekat_nrt.service.KorisnikService;

import java.util.List;

@RestController
@RequestMapping("/korisnik")
@RequiredArgsConstructor
public class KorisnikController {

    private final KorisnikService korisnikService;

    @PostMapping
    public ResponseEntity<AddKorisnikResponse> add(@Valid @RequestBody AddKorisnikRequest req) {
        return ResponseEntity.ok(korisnikService.add(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateKorisnikResponse> update(@PathVariable Integer id, @Valid @RequestBody UpdateKorisnikRequest req) {
        return ResponseEntity.ok(korisnikService.update(id, req));
    }

    @GetMapping
    public ResponseEntity<List<FindKorisnikResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(korisnikService.findAll(page, size));
    }

    @GetMapping("{id}")
    public ResponseEntity<FindKorisnikResponse> getKorisnik(@PathVariable int id){
        return ResponseEntity.ok(korisnikService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        return ResponseEntity.ok(korisnikService.delete(id));
    }

    @PostMapping("/{id}/rezultati")
    public ResponseEntity<AddRezultatKorisnikaResponse> addRezultat(
            @PathVariable int id,
            @Valid @RequestBody AddRezultatKorisnikaRequest req
    ) {
        return ResponseEntity.ok(korisnikService.addRezultat(id, req));
    }

    @GetMapping("/{id}/rezultati")
    public ResponseEntity<List<FindRezultatKorisnikaResponse>> getRezultati(
            @PathVariable int id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(korisnikService.getRezultati(id, page, size));
    }
}
