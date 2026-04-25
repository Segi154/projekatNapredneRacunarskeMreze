package rs.ac.bg.fon.projekat_nrt.mapper;

import org.mapstruct.*;
import rs.ac.bg.fon.projekat_nrt.domain.Trener;
import rs.ac.bg.fon.projekat_nrt.requestDTO.trener.AddTrenerRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trener.AddTrenerResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trener.FindTrenerResponse;
import rs.ac.bg.fon.projekat_nrt.responseDTO.trener.UpdateTrenerResponse;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TrenerMapper {


    Trener toEntity(AddTrenerRequest req);
    AddTrenerResponse toAddResponse(Trener entity);

    UpdateTrenerResponse toUpdateResponse(Trener entity);

    FindTrenerResponse toSearchResponse(Trener entity);


}

