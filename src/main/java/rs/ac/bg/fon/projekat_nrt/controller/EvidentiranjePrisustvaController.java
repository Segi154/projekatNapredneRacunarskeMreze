package rs.ac.bg.fon.projekat_nrt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.projekat_nrt.requestDTO.evidentiranje_prisustva.AddEvidentiranjePrisustvaRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.evidentiranje_prisustva.UpdateEvidentiranjePrisustvaRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.evidentiranje_prisustva.AddComprehensiveEPResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.evidentiranje_prisustva.FindEvidentiranjePrisustvaResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.evidentiranje_prisustva.UpdateComprehensiveEPResponse;
import rs.ac.bg.fon.projekat_nrt.service.EvidentiranjePrisustvaService;

import java.util.List;

@RestController
@RequestMapping("/prisustvo")
@RequiredArgsConstructor
public class EvidentiranjePrisustvaController {

    private final EvidentiranjePrisustvaService prisustvoService;

    @PostMapping
    public ResponseEntity<AddComprehensiveEPResponse> add(
            @Valid @RequestBody List<@Valid AddEvidentiranjePrisustvaRequest> req
    ) {
        return ResponseEntity.ok(prisustvoService.add(req));
    }

    @PutMapping
    public ResponseEntity<UpdateComprehensiveEPResponse> update(
            @Valid @RequestBody List<@Valid UpdateEvidentiranjePrisustvaRequest> req
    ) {
        return ResponseEntity.ok(prisustvoService.update(req));
    }

    @GetMapping
    public ResponseEntity<List<FindEvidentiranjePrisustvaResponse>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return ResponseEntity.ok(prisustvoService.findAll(page, size));
    }

    @GetMapping("/{treningId}/{korisnikId}")
    public ResponseEntity<FindEvidentiranjePrisustvaResponse> getById(
            @PathVariable int treningId,
            @PathVariable int korisnikId
    ) {
        return ResponseEntity.ok(prisustvoService.findById(treningId, korisnikId));
    }

    @DeleteMapping("/{treningId}/{korisnikId}")
    public ResponseEntity<String> delete(
            @PathVariable int treningId,
            @PathVariable int korisnikId
    ) {
        return ResponseEntity.ok(prisustvoService.delete(treningId, korisnikId));
    }
}
