package vrzon.tennisclubapi.facade.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import vrzon.tennisclubapi.api.dtos.create.CourtCreateDto;
import vrzon.tennisclubapi.api.dtos.read.CourtReadDto;
import vrzon.tennisclubapi.api.dtos.bases.CourtBaseDto;
import vrzon.tennisclubapi.data.models.Court;

@Mapper(componentModel = "spring", uses = { SurfaceMapper.class })
public interface CourtMapper extends GenericMapper<Court, CourtReadDto, CourtCreateDto, CourtBaseDto> {

    @Override
    @Mapping(target = "surface.id", source = "surfaceId")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    Court mapCreateDtoToModel(CourtCreateDto createDto);
}
