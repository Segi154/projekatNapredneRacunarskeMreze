package rs.ac.bg.fon.projekat_nrt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.projekat_nrt.domain.*;
import rs.ac.bg.fon.projekat_nrt.exception.NotFoundException;
import rs.ac.bg.fon.projekat_nrt.exception.SameTrainingShouldBeRecorded;
import rs.ac.bg.fon.projekat_nrt.mapper.EvidentiranjePrisustvaMapper;
import rs.ac.bg.fon.projekat_nrt.mapper.TreningMapper;
import rs.ac.bg.fon.projekat_nrt.repository.*;
import rs.ac.bg.fon.projekat_nrt.requestDTO.evidentiranje_prisustva.AddEvidentiranjePrisustvaRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.evidentiranje_prisustva.UpdateEvidentiranjePrisustvaRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.evidentiranje_prisustva.*;
import rs.ac.bg.fon.projekat_nrt.service.EvidentiranjePrisustvaService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EvidentiranjePrisustvaServiceImpl implements EvidentiranjePrisustvaService {

    private final EvidentiranjePrisustvaRepository prisustvoRepository;
    private final KorisnikRepository korisnikRepository;
    private final TreningRepository treningRepository;
    private final EvidentiranjePrisustvaMapper prisustvoMapper;
    private final TreningMapper treningMapper;

    @Override
    @Transactional
    public AddComprehensiveEPResponse add(List<AddEvidentiranjePrisustvaRequest> req) {

        validateSingleTreningIdAdd(req);
        Trening trening = treningRepository.findById(req.getFirst().getTreningId())
                .orElseThrow(() -> new NotFoundException("Trening nije pronađen!Id treninga: " + req.getFirst().getTreningId()));
        List<EvidentiranjePrisustva> entities = req.stream().map(r -> {

            EvidentiranjePrisustvaId id = new EvidentiranjePrisustvaId(r.getTreningId(), r.getKorisnikId());
            if (prisustvoRepository.existsById(id)) {
                throw new NotFoundException("Evidencija već postoji za treningId=" + r.getTreningId() + " i korisnikId=" + r.getKorisnikId());
            }

            Korisnik korisnik = korisnikRepository.findById(r.getKorisnikId())
                    .orElseThrow(() -> new NotFoundException("Korisnik nije pronađen.Id korisnika: " + r.getKorisnikId()));



            EvidentiranjePrisustva ep = prisustvoMapper.toEntity(r);
            ep.setId(id);
            ep.setKorisnik(korisnik);
            ep.setTrening(trening);

            return ep;
        }).toList();


        List<EvidentiranjePrisustva> saved = prisustvoRepository.saveAll(entities);

        List<AddEvidentiranjePrisustvaResponse> evidentiranjePrisustvaResponses =
                 saved.stream()
                .map(prisustvoMapper::toAddResponse)
                .toList();
        AddComprehensiveEPResponse addComprehensiveEPResponse = new AddComprehensiveEPResponse();
        addComprehensiveEPResponse.setEvidentiranjePrisustvaResponse(evidentiranjePrisustvaResponses);
        addComprehensiveEPResponse.setTrening(treningMapper.toAddResponse(trening));
        return addComprehensiveEPResponse;
    }

    @Override
    @Transactional
    public UpdateComprehensiveEPResponse update(List<UpdateEvidentiranjePrisustvaRequest> req) {

        validateSingleTreningIdUpdate(req);
        Trening trening = treningRepository.findById(req.getFirst().getTreningId())
                .orElseThrow(() -> new NotFoundException("Trening nije pronađen!Id treninga: " + req.getFirst().getTreningId()));
        List<EvidentiranjePrisustva> updatedEntities = req.stream().map(r -> {

            EvidentiranjePrisustvaId id = new EvidentiranjePrisustvaId(r.getTreningId(), r.getKorisnikId());
            EvidentiranjePrisustva evidencija = prisustvoRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException(
                            "Evidencija nije pronađena za treningId=" + r.getTreningId() + " i korisnikId=" + r.getKorisnikId()
                    ));

            Korisnik korisnik = korisnikRepository.findById(r.getKorisnikId())
                    .orElseThrow(() -> new NotFoundException("Korisnik nije pronađen: " + r.getKorisnikId()));


            evidencija.setPotroseneKal(r.getPotroseneKal());
            evidencija.setOtkucajiSrca(r.getOtkucajiSrca());

            evidencija.setKorisnik(korisnik);
            evidencija.setTrening(trening);

            return evidencija;
        }).toList();


        List<UpdateEvidentiranjePrisustvaResponse> evidentiranjePrisustvaResponses =
                updatedEntities.stream()
                        .map(prisustvoMapper::toUpdateResponse)
                        .toList();
        UpdateComprehensiveEPResponse updateComprehensiveEPResponse = new UpdateComprehensiveEPResponse();
        updateComprehensiveEPResponse.setEvidentiranjePrisustvaResponse(evidentiranjePrisustvaResponses);
        updateComprehensiveEPResponse.setTrening(treningMapper.toUpdateResponse(trening));
        return updateComprehensiveEPResponse;
    }

    @Override
    @Transactional
    public List<FindEvidentiranjePrisustvaResponse> findAll(int page, int size) {
        return getAllPrisustvo(page, size)
                .getContent()
                .stream()
                .map(prisustvoMapper::toFindResponse)
                .toList();
    }

    @Override
    public FindEvidentiranjePrisustvaResponse findById(int treningId, int korisnikId) {
        EvidentiranjePrisustvaId id = new EvidentiranjePrisustvaId(treningId, korisnikId);
        EvidentiranjePrisustva evidencija = prisustvoRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Evidencija nije pronađena za treningId=" + treningId + " i korisnikId=" + korisnikId));

        return prisustvoMapper.toFindResponse(evidencija);
    }

    private Page<EvidentiranjePrisustva> getAllPrisustvo(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return prisustvoRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public String delete(int treningId, int korisnikId) {
        EvidentiranjePrisustvaId id = new EvidentiranjePrisustvaId(treningId, korisnikId);
        if (!prisustvoRepository.existsById(id)) {
            throw new NotFoundException("Evidencija nije pronađena za treningId=" + treningId + " i korisnikId=" + korisnikId);
        }
        prisustvoRepository.deleteById(id);
        return "Uspešno izbrisana evidencija za treningId=" + treningId + " i korisnikId=" + korisnikId;
    }

    private void validateSingleTreningIdAdd(List<AddEvidentiranjePrisustvaRequest> req) {
        Integer t = req.getFirst().getTreningId();
        if (req.stream().anyMatch(r -> !t.equals(r.getTreningId()))) {
            throw new SameTrainingShouldBeRecorded("Morate evidentirati jedan trening, nije moguce u isto vreme vise njih!!.");
        }
    }

    private void validateSingleTreningIdUpdate(List<UpdateEvidentiranjePrisustvaRequest> req) {
        Integer t = req.getFirst().getTreningId();
        if (req.stream().anyMatch(r -> !t.equals(r.getTreningId()))) {
            throw new SameTrainingShouldBeRecorded("Morate evidentirati jedan trening, nije moguce u isto vreme vise njih!!!.");
        }
    }
}
