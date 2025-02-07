package vrzon.tennisclubapi.facade;

import org.springframework.stereotype.Service;
import vrzon.tennisclubapi.api.dtos.read.SurfaceReadDto;
import vrzon.tennisclubapi.api.dtos.bases.SurfaceBaseDto;
import vrzon.tennisclubapi.data.models.Surface;
import vrzon.tennisclubapi.facade.mappers.SurfaceMapper;
import vrzon.tennisclubapi.services.SurfaceService;

@Service
public class SurfaceFacade extends FacadeBase<Surface, SurfaceReadDto, SurfaceBaseDto, SurfaceBaseDto> {
    public SurfaceFacade(SurfaceService service, SurfaceMapper mapper) {
        super(service, mapper);
    }
}
