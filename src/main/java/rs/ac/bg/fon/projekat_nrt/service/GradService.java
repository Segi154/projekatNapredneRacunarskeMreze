package rs.ac.bg.fon.projekat_nrt.service;

import rs.ac.bg.fon.projekat_nrt.requestDTO.grad.GradRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.grad.GradResponse;

public interface GradService {


    GradResponse findById(int id);

    GradResponse add(GradRequest gradRequest);
}
