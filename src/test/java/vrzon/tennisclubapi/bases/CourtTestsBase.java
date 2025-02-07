package vrzon.tennisclubapi.bases;

import vrzon.tennisclubapi.api.dtos.create.CourtCreateDto;
import vrzon.tennisclubapi.api.dtos.read.CourtReadDto;
import vrzon.tennisclubapi.data.models.Court;
import vrzon.tennisclubapi.data.models.Surface;

public class CourtTestsBase extends TestsBase {
    private final SurfaceTestsBase surfaceTestsBase = new SurfaceTestsBase();

    public final Surface surface = surfaceTestsBase.surface;
    public final long courtNumber = 1;
    public final Court court = new Court(courtNumber, surface);

    protected final CourtCreateDto createDto = new CourtCreateDto();
    protected final CourtCreateDto updateDto = new CourtCreateDto();
    protected final CourtReadDto readDto = new CourtReadDto();
}
