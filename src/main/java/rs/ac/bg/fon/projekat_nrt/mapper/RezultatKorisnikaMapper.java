package rs.ac.bg.fon.projekat_nrt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import rs.ac.bg.fon.projekat_nrt.domain.RezultatKorisnika;
import rs.ac.bg.fon.projekat_nrt.requestDTO.rezultat_korisnika.AddRezultatKorisnikaRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.rezultat_korisnika.AddRezultatKorisnikaResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.rezultat_korisnika.FindRezultatKorisnikaResponse;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RezultatKorisnikaMapper {

    RezultatKorisnika toEntity(AddRezultatKorisnikaRequest req);

    @Mapping(source = "korisnik.idKorisnika", target = "korisnikId")
    @Mapping(source = "id.idRez", target = "idRez")
    AddRezultatKorisnikaResponse toAddResponse(RezultatKorisnika entity);

    @Mapping(source = "korisnik.idKorisnika", target = "korisnikId")
    @Mapping(source = "id.idRez", target = "idRez")
    FindRezultatKorisnikaResponse toFindResponse(RezultatKorisnika entity);
}
