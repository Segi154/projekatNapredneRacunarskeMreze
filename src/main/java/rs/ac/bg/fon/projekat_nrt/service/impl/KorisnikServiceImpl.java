package rs.ac.bg.fon.projekat_nrt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import rs.ac.bg.fon.projekat_nrt.domain.*;
import rs.ac.bg.fon.projekat_nrt.exception.NotFoundException;
import rs.ac.bg.fon.projekat_nrt.mapper.KorisnikMapper;
import rs.ac.bg.fon.projekat_nrt.mapper.RezultatKorisnikaMapper;
import rs.ac.bg.fon.projekat_nrt.repository.*;
import rs.ac.bg.fon.projekat_nrt.requestDTO.korisnik.AddKorisnikRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.rezultat_korisnika.AddRezultatKorisnikaRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.korisnik.UpdateKorisnikRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.korisnik.AddKorisnikResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.korisnik.FindKorisnikResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.korisnik.UpdateKorisnikResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.rezultat_korisnika.AddRezultatKorisnikaResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.rezultat_korisnika.FindRezultatKorisnikaResponse;
import rs.ac.bg.fon.projekat_nrt.service.KorisnikService;

import java.util.*;

@Service
@RequiredArgsConstructor
public class KorisnikServiceImpl implements KorisnikService {

    private final KorisnikRepository korisnikRepository;
    private final GradRepository gradRepository;
    private final TipTreningaRepository tipTreningaRepository;
    private final KorisnikMapper korisnikMapper;
    private final RezultatKorisnikaRepository rezultatKorisnikaRepository;
    private final RezultatKorisnikaMapper rezultatKorisnikaMapper;

    @Override
    @Transactional
    public AddKorisnikResponse add(AddKorisnikRequest req) {
        Grad grad = gradRepository.findById(req.getGradId())
                .orElseThrow(() -> new NotFoundException("Grad nije pronađen: " + req.getGradId()));

        List<TipTreninga> tipovi = tipTreningaRepository.findAllById(req.getTipoviIds());
        validateAllTipIdsExist(req.getTipoviIds(), tipovi);
        Korisnik korisnik = korisnikMapper.toEntity(req);
        korisnik.setGrad(grad);
        korisnik.setTipovi(tipovi);

        Korisnik saved = korisnikRepository.save(korisnik);
        return korisnikMapper.toAddResponse(saved);
    }

    @Override
    @Transactional
    public UpdateKorisnikResponse update(Integer id, UpdateKorisnikRequest req) {

        Korisnik korisnik = korisnikRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Korisnik nije pronađen: " + id));

        Grad grad = gradRepository.findById(req.getGradId())
                .orElseThrow(() -> new NotFoundException("Grad nije pronađen: " + req.getGradId()));

        List<TipTreninga> tipovi = tipTreningaRepository.findAllById(req.getTipoviIds());
        validateAllTipIdsExist(req.getTipoviIds(), tipovi);

        korisnik.setGrad(grad);
        korisnik.setTipovi(tipovi);
        korisnik.setIme(req.getIme());
        korisnik.setPrezime(req.getPrezime());
        korisnik.setKontakt(req.getKontakt());
        korisnik.setDatumRodjenja(req.getDatumRodjenja());
        korisnik.setAdresa(req.getAdresa());

        return korisnikMapper.toUpdateResponse(korisnik);
    }

    @Override
    @Transactional
    public List<FindKorisnikResponse> findAll(int page, int size) {
        return getAllKorisnik(page, size)
                .getContent()
                .stream()
                .map(korisnikMapper::toFindResponse)
                .toList();
    }

    @Override
    public FindKorisnikResponse findById(int id) {
        Korisnik korisnik = korisnikRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Korisnik nije pronađen: " + id));
        return korisnikMapper.toFindResponse(korisnik);
    }

    private Page<Korisnik> getAllKorisnik(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return korisnikRepository.findAll(pageable);
    }


    @Override
    @Transactional
    public String delete(Integer id) {
        if (!korisnikRepository.existsById(id)) {
            throw new NotFoundException("Korisnik sa id: " +id+" nije pronađen.");
        }
        korisnikRepository.deleteById(id);
        return "Uspešno izbrisan korisnik sa id=" + id;
    }

    @Override
    @Transactional
    public AddRezultatKorisnikaResponse addRezultat(int korisnikId, AddRezultatKorisnikaRequest req) {

        Korisnik korisnik = korisnikRepository.findById(korisnikId)
                .orElseThrow(() -> new NotFoundException("Korisnik nije pronađen. Id korisnika: " + korisnikId));

        Integer max = rezultatKorisnikaRepository.maxIdRezForKorisnik(korisnikId);
        int nextIdRez = (max == null ? 1 : max + 1);

        RezultatKorisnika rezultat = rezultatKorisnikaMapper.toEntity(req);
        rezultat.setKorisnik(korisnik);
        rezultat.setId(new RezultatKorisnikaId(korisnikId, nextIdRez));

        RezultatKorisnika saved = rezultatKorisnikaRepository.save(rezultat);
        System.out.println("Korisnik id "+saved.getKorisnik().getIdKorisnika());
        System.out.println("Id rezultat: "+saved.getId());

        return rezultatKorisnikaMapper.toAddResponse(saved);
    }


    @Override
    @Transactional
    public List<FindRezultatKorisnikaResponse> getRezultati(int korisnikId, int page, int size) {

        if (!korisnikRepository.existsById(korisnikId)) {
            throw new NotFoundException("Korisnik nije pronađen. Id korisnika: " + korisnikId);
        }

        return getRezultatiKorisnikaPage(korisnikId, page, size)
                .getContent()
                .stream()
                .map(rezultatKorisnikaMapper::toFindResponse)
                .toList();
    }

    private Page<RezultatKorisnika> getRezultatiKorisnikaPage(int korisnikId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return rezultatKorisnikaRepository
                .findByKorisnik_IdKorisnikaOrderByDatumRezultataDesc(korisnikId, pageable);
    }

    private void validateAllTipIdsExist(List<Integer> requestedIds, List<TipTreninga> found) {
        Set<Integer> foundIds = found.stream().map(TipTreninga::getIdTipa).collect(java.util.stream.Collectors.toSet());
        List<Integer> missing = requestedIds.stream().distinct().filter(id -> !foundIds.contains(id)).toList();
        if (!missing.isEmpty()) {
            throw new NotFoundException("Tip treninga ne postoji za id-jeve: " + missing);
        }
    }
}
