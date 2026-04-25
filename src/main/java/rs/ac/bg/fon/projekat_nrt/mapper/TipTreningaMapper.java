package rs.ac.bg.fon.projekat_nrt.mapper;


import org.mapstruct.Mapper;
import rs.ac.bg.fon.projekat_nrt.domain.TipTreninga;
import rs.ac.bg.fon.projekat_nrt.requestDTO.tip_treninga.TipTreningaRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.tip_treninga.TipTreningaResponse;

@Mapper(componentModel = "spring")
public interface TipTreningaMapper {
    TipTreninga toEntity(TipTreningaRequest req);
    TipTreningaResponse toResponse(TipTreninga entity);
}

