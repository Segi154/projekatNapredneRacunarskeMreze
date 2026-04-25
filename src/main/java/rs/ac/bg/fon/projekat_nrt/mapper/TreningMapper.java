package rs.ac.bg.fon.projekat_nrt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import rs.ac.bg.fon.projekat_nrt.domain.Trening;
import rs.ac.bg.fon.projekat_nrt.requestDTO.trening.AddTreningRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trening.AddTreningResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trening.FindTreningResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trening.UpdateTreningResponse;

@Mapper(
        componentModel = "spring",
        uses = { TrenerMapper.class, TipTreningaMapper.class }
)
public interface TreningMapper {

    @Mapping(target = "idTreninga", ignore = true)
    Trening toEntity(AddTreningRequest req);

    AddTreningResponse toAddResponse(Trening entity);

    UpdateTreningResponse toUpdateResponse(Trening entity);

    FindTreningResponse toSearchResponse(Trening entity);


}
