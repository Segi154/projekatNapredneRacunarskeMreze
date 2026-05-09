package rs.ac.bg.fon.projekat_nrt.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.bg.fon.projekat_nrt.requestDTO.trener.AddTrenerRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.trener.UpdateTrenerRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trener.AddTrenerResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trener.FindTrenerResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trener.UpdateTrenerResponse;
import rs.ac.bg.fon.projekat_nrt.service.TrenerService;

import java.util.List;

@RestController
@RequestMapping("/trener")
@RequiredArgsConstructor
public class TrenerController {

    private final TrenerService trenerService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AddTrenerResponse> add(@Valid @RequestPart("trenerData") AddTrenerRequest req, @RequestPart("file") MultipartFile file) {


        System.out.println();
        System.out.println();
        System.out.println();



        System.out.println();

        System.out.println();

        System.out.println();


        System.out.println(req.getGodineIskustva()+" !!!");
        System.out.println("PROBA!");



        System.out.println("test123");


        System.out.println("test1234");


        System.out.println("TESTTEST");

        return ResponseEntity.status(HttpStatus.CREATED).body(trenerService.add(req,file));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateTrenerResponse> update(@PathVariable Integer id, @Valid @RequestBody UpdateTrenerRequest req) {
        System.out.println("TEST!");

        System.out.println("volim");

        int x=5;
        return ResponseEntity.ok(trenerService.update(id, req));
    }

    @GetMapping
    public ResponseEntity<List<FindTrenerResponse>> getAllTreners(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "10") int size) {

        return ResponseEntity.ok(trenerService.findAll(page, size));
    }

    @GetMapping("{id}")
    public ResponseEntity<FindTrenerResponse> getTrener(@PathVariable int id){

        return ResponseEntity.ok(trenerService.findTrenerById(id));

    }
}
