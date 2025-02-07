package vrzon.tennisclubapi.facade.mappers;

import org.mapstruct.Mapper;
import vrzon.tennisclubapi.api.dtos.read.SurfaceReadDto;
import vrzon.tennisclubapi.api.dtos.bases.SurfaceBaseDto;
import vrzon.tennisclubapi.data.models.Surface;

@Mapper(componentModel = "spring")
public interface SurfaceMapper extends GenericMapper<Surface, SurfaceReadDto, SurfaceBaseDto, SurfaceBaseDto> {
}
