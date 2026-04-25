package rs.ac.bg.fon.projekat_nrt.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import rs.ac.bg.fon.projekat_nrt.domain.Trener;
import rs.ac.bg.fon.projekat_nrt.exception.NotFoundException;
import rs.ac.bg.fon.projekat_nrt.mapper.TrenerMapper;
import rs.ac.bg.fon.projekat_nrt.repository.TrenerRepository;
import rs.ac.bg.fon.projekat_nrt.requestDTO.trener.AddTrenerRequest;
import rs.ac.bg.fon.projekat_nrt.requestDTO.trener.UpdateTrenerRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trener.AddTrenerResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trener.FindTrenerResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trener.UpdateTrenerResponse;
import rs.ac.bg.fon.projekat_nrt.service.TrenerService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrenerServiceImpl implements TrenerService {

    private final TrenerRepository trenerRepository;
    private final TrenerMapper trenerMapper;


    @Transactional
    @Override
    public AddTrenerResponse add(AddTrenerRequest req, MultipartFile file) {
        Trener trener = trenerMapper.toEntity(req);
        if (file != null && !file.isEmpty()) {
            String fileName = saveImage(file);
            trener.setPutanjaDoFajla(fileName);
        }
        System.out.println("Godina iskustva su: "+trener.getGodineIskustva());
        Trener saved = trenerRepository.save(trener);
        return trenerMapper.toAddResponse(saved);
    }

    private String saveImage(MultipartFile file) {
        try {
            Path uploadDir = Paths.get("./uploads/treneri");
            Files.createDirectories(uploadDir);

            String originalName = file.getOriginalFilename();
            String extension = originalName != null && originalName.contains(".")
                    ? originalName.substring(originalName.lastIndexOf("."))
                    : ".jpg";
            String fileName = UUID.randomUUID() + extension;

            Path filePath = uploadDir.resolve(fileName);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Greška pri čuvanju slike", e);
        }
    }

    @Transactional
    @Override
    public UpdateTrenerResponse update(Integer id, UpdateTrenerRequest req) {
        Trener trener = trenerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Trener nije pronadjen: " + id));

        trener.setIme(req.getIme());
        trener.setPrezime(req.getPrezime());
        trener.setKontakt(req.getKontakt());
        trener.setSertifikat(req.getSertifikat());
        trener.setGodineIskustva(req.getGodineIskustva());

        return trenerMapper.toUpdateResponse(trener);
    }

    @Override
    public List<FindTrenerResponse> findAll(int page, int size) {
        return getAllTrener(page,size)
                .getContent()
                .stream()
                .map(trenerMapper::toSearchResponse)
                .toList();
    }

    @Override
    public FindTrenerResponse findTrenerById(int id) {
        Trener trener = trenerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Trener nije pronadjen: " + id));
        return trenerMapper.toSearchResponse(trener);
    }

    private Page<Trener> getAllTrener(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return trenerRepository.findAll(pageable);
    }


}
