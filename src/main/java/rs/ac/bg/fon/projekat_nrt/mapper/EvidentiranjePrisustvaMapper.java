package rs.ac.bg.fon.projekat_nrt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import rs.ac.bg.fon.projekat_nrt.domain.EvidentiranjePrisustva;
import rs.ac.bg.fon.projekat_nrt.requestDTO.evidentiranje_prisustva.AddEvidentiranjePrisustvaRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.evidentiranje_prisustva.AddEvidentiranjePrisustvaResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.evidentiranje_prisustva.FindEvidentiranjePrisustvaResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.evidentiranje_prisustva.UpdateEvidentiranjePrisustvaResponse;

@Mapper(componentModel = "spring", uses = { KorisnikMapper.class, TreningMapper.class },
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EvidentiranjePrisustvaMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "trening", ignore = true)
    EvidentiranjePrisustva toEntity(AddEvidentiranjePrisustvaRequest req);

    AddEvidentiranjePrisustvaResponse toAddResponse(EvidentiranjePrisustva entity);

    UpdateEvidentiranjePrisustvaResponse toUpdateResponse(EvidentiranjePrisustva entity);
    FindEvidentiranjePrisustvaResponse toFindResponse(EvidentiranjePrisustva entity);
}
