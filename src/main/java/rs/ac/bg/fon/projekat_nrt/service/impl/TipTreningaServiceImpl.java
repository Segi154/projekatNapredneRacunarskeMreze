package rs.ac.bg.fon.projekat_nrt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.projekat_nrt.domain.TipTreninga;
import rs.ac.bg.fon.projekat_nrt.exception.NotFoundException;
import rs.ac.bg.fon.projekat_nrt.mapper.TipTreningaMapper;
import rs.ac.bg.fon.projekat_nrt.repository.TipTreningaRepository;
import rs.ac.bg.fon.projekat_nrt.requestDTO.tip_treninga.TipTreningaRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.tip_treninga.TipTreningaResponse;
import rs.ac.bg.fon.projekat_nrt.service.TipTreningaService;

@Service
@RequiredArgsConstructor
public class TipTreningaServiceImpl implements TipTreningaService {

    private final TipTreningaRepository tipTreningaRepository;
    private final TipTreningaMapper tipTreningaMapper;
    @Override
    public TipTreningaResponse findById(int id) {
        TipTreninga tipTreninga = tipTreningaRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Tip treninga nije pronađen: " + id));

        return tipTreningaMapper.toResponse(tipTreninga);

    }

    @Override
    @Transactional
    public TipTreningaResponse addTip(TipTreningaRequest req) {
        TipTreninga tipTreninga = tipTreningaMapper.toEntity(req);
        tipTreninga = tipTreningaRepository.save(tipTreninga);
        return tipTreningaMapper.toResponse(tipTreninga);
    }
}
