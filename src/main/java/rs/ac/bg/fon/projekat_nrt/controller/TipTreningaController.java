package rs.ac.bg.fon.projekat_nrt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.projekat_nrt.requestDTO.tip_treninga.TipTreningaRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.tip_treninga.TipTreningaResponse;
import rs.ac.bg.fon.projekat_nrt.service.TipTreningaService;

@RestController
@RequestMapping("/tip")
@RequiredArgsConstructor
public class TipTreningaController {

    private final TipTreningaService tipTreningaService;

    @GetMapping("{id}")
    public ResponseEntity<TipTreningaResponse> findById(@PathVariable int id){
        return ResponseEntity.ok(tipTreningaService.findById(id));
    }

    @PostMapping
    public ResponseEntity<TipTreningaResponse> addTip(@RequestBody TipTreningaRequest req){
        return ResponseEntity.ok(tipTreningaService.addTip(req));
    }
}
