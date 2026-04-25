package rs.ac.bg.fon.projekat_nrt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.projekat_nrt.domain.TipTreninga;
import rs.ac.bg.fon.projekat_nrt.domain.Trener;
import rs.ac.bg.fon.projekat_nrt.domain.Trening;
import rs.ac.bg.fon.projekat_nrt.exception.NotFoundException;
import rs.ac.bg.fon.projekat_nrt.mapper.TreningMapper;
import rs.ac.bg.fon.projekat_nrt.repository.TipTreningaRepository;
import rs.ac.bg.fon.projekat_nrt.repository.TrenerRepository;
import rs.ac.bg.fon.projekat_nrt.repository.TreningRepository;
import rs.ac.bg.fon.projekat_nrt.requestDTO.trening.AddTreningRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.trening.UpdateTreningRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trening.AddTreningResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trening.FindTreningResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trening.UpdateTreningResponse;
import rs.ac.bg.fon.projekat_nrt.service.TreningService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreningServiceImpl implements TreningService {

    private final TreningRepository treningRepository;
    private final TreningMapper treningMapper;
    private final TrenerRepository trenerRepository;
    private final TipTreningaRepository tipTreningaRepository;

    @Override
    @Transactional
    public AddTreningResponse add(AddTreningRequest req) {
        var tip = tipTreningaRepository.findById(req.getTipId())
                .orElseThrow(() -> new NotFoundException("Tip treninga nije pronađen: " + req.getTipId()));

        var trener = trenerRepository.findById(req.getTrenerId())
                .orElseThrow(() -> new NotFoundException("Trener nije pronađen: " + req.getTrenerId()));

        Trening trening = treningMapper.toEntity(req);
        trening.setTip(tip);
        trening.setTrener(trener);

        Trening saved = treningRepository.save(trening);
        return treningMapper.toAddResponse(saved);
    }

    @Override
    @Transactional
    public UpdateTreningResponse update(Integer id, UpdateTreningRequest req) {

        Trening trening = treningRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Trening nije pronađen: " + id));

        Trener trener = trenerRepository.findById(req.getTrenerId())
                .orElseThrow(() -> new NotFoundException("Trener nije pronađen: " + req.getTrenerId()));

        TipTreninga tip = tipTreningaRepository.findById(req.getTipId())
                .orElseThrow(() -> new NotFoundException("Tip treninga nije pronađen: " + req.getTipId()));


        trening.setTrener(trener);
        trening.setTip(tip);
        trening.setCena(req.getCena());
        trening.setDatumTreninga(req.getDatumTreninga());
        trening.setTrajanjeUMin(req.getTrajanjeUMin());

        return treningMapper.toUpdateResponse(trening);


    }

    @Override
    public List<FindTreningResponse> findAll(int page, int size) {
        return getAllTrening(page, size)
                .getContent()
                .stream()
                .map(treningMapper::toSearchResponse)
                .toList();
    }


    private Page<Trening> getAllTrening(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return treningRepository.findAll(pageable);
    }


    @Override
    @Transactional
    public void delete(Integer id) {
        if (!treningRepository.existsById(id)) {
            throw new NotFoundException("Trening nije pronađen: " + id);
        }
        treningRepository.deleteById(id);
    }
}

