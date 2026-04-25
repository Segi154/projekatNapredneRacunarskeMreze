package rs.ac.bg.fon.projekat_nrt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.projekat_nrt.requestDTO.trening.AddTreningRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.trening.UpdateTreningRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trening.AddTreningResponse;

import rs.ac.bg.fon.projekat_nrt.responseDTO.trening.FindTreningResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trening.UpdateTreningResponse;
import rs.ac.bg.fon.projekat_nrt.service.TreningService;

import java.util.List;

@RestController
@RequestMapping("/trening")
@RequiredArgsConstructor
public class TreningController {

    private final TreningService treningService;

    @PostMapping
    public ResponseEntity<AddTreningResponse> add(@Valid @RequestBody AddTreningRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(treningService.add(req));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateTreningResponse> update(@PathVariable Integer id, @Valid @RequestBody UpdateTreningRequest req) {
        return ResponseEntity.ok(treningService.update(id, req));
    }

    @GetMapping
    public ResponseEntity<List<FindTreningResponse>> getAll(@RequestParam(defaultValue = "0") int page,
                                                            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(treningService.findAll(page,size));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        treningService.delete(id);
        return ResponseEntity.ok("Uspešno izbrisan trening sa id=" + id);
    }
}

