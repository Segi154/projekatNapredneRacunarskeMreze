package rs.ac.bg.fon.projekat_nrt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rs.ac.bg.fon.projekat_nrt.domain.Grad;
import rs.ac.bg.fon.projekat_nrt.exception.NotFoundException;
import rs.ac.bg.fon.projekat_nrt.mapper.GradMapper;
import rs.ac.bg.fon.projekat_nrt.repository.GradRepository;
import rs.ac.bg.fon.projekat_nrt.requestDTO.grad.GradRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.grad.GradResponse;
import rs.ac.bg.fon.projekat_nrt.service.GradService;

@Service
@RequiredArgsConstructor
public class GradServiceImpl implements GradService {

    private final GradRepository gradRepository;
    private final GradMapper gradMapper;


    @Override
    public GradResponse findById(int id) {
        Grad grad = gradRepository.findById(id).orElseThrow(
                ()->   new NotFoundException("Ne postoji trazeni grad!")
        );
        return gradMapper.toAddResponse(grad);
    }

    @Override
    public GradResponse add(GradRequest gradRequest) {

        Grad grad = gradMapper.toEntity(gradRequest);
        grad = gradRepository.save(grad);
        return gradMapper.toAddResponse(grad);
    }
}
