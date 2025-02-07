package vrzon.tennisclubapi.api.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vrzon.tennisclubapi.api.dtos.read.SurfaceReadDto;
import vrzon.tennisclubapi.api.dtos.bases.SurfaceBaseDto;
import vrzon.tennisclubapi.data.models.Surface;
import vrzon.tennisclubapi.facade.SurfaceFacade;

@RestController
@RequestMapping(path = "/api/surface")
public final class SurfaceController extends ControllerBase<Surface, SurfaceReadDto, SurfaceBaseDto, SurfaceBaseDto> {
    public SurfaceController(SurfaceFacade facade) {
        super(facade);
    }
}
