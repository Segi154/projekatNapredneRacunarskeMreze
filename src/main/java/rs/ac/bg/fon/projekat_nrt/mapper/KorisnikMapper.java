package rs.ac.bg.fon.projekat_nrt.mapper;

import org.mapstruct.*;
import rs.ac.bg.fon.projekat_nrt.domain.Korisnik;
import rs.ac.bg.fon.projekat_nrt.requestDTO.korisnik.AddKorisnikRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.korisnik.AddKorisnikResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.korisnik.FindKorisnikResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.korisnik.UpdateKorisnikResponse;

@Mapper(componentModel = "spring", uses = { GradMapper.class, TipTreningaMapper.class })
public interface KorisnikMapper {

    @Mapping(target = "idKorisnika", ignore = true)
    Korisnik toEntity(AddKorisnikRequest req);
    AddKorisnikResponse toAddResponse(Korisnik entity);
    UpdateKorisnikResponse toUpdateResponse(Korisnik entity);
    FindKorisnikResponse toFindResponse(Korisnik entity);
}
