package rs.ac.bg.fon.projekat_nrt.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import rs.ac.bg.fon.projekat_nrt.domain.Grad;
import rs.ac.bg.fon.projekat_nrt.requestDTO.grad.GradRequest;
import rs.ac.bg.fon.projekat_nrt.responseDTO.grad.GradResponse;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface GradMapper {

    Grad toEntity(GradRequest req);
    GradResponse toAddResponse(Grad entity);

}
