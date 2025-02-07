package vrzon.tennisclubapi.bases;

import vrzon.tennisclubapi.api.dtos.create.SurfaceCreateDto;
import vrzon.tennisclubapi.api.dtos.read.SurfaceReadDto;
import vrzon.tennisclubapi.data.models.Surface;

public class SurfaceTestsBase extends TestsBase {
    public final Surface surface = new Surface("Concrete", 1.23f);

    protected final SurfaceReadDto readDto = new SurfaceReadDto();
    protected final SurfaceCreateDto createDto = new SurfaceCreateDto();
    protected final SurfaceCreateDto updateDto = new SurfaceCreateDto();
}
