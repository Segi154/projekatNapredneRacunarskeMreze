package rs.ac.bg.fon.projekat_nrt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.bg.fon.projekat_nrt.requestDTO.grad.GradRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.grad.GradResponse;
import rs.ac.bg.fon.projekat_nrt.service.GradService;

@RestController
@RequestMapping("/grad")
@RequiredArgsConstructor
public class GradController {

    private final GradService gradService;

    @GetMapping("{id}")
    public ResponseEntity<GradResponse> findById(@PathVariable int id ){
       return ResponseEntity.ok(gradService.findById(id));
    }

    @PostMapping
    public ResponseEntity<GradResponse> add(@Valid @RequestBody GradRequest gradRequest){

        return ResponseEntity.status(Response.SC_CREATED).body(gradService.add(gradRequest));
    }
}
