package vrzon.tennisclubapi.api.dtos.read;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrzon.tennisclubapi.api.dtos.bases.CourtBaseDto;

@Getter
@Setter
@NoArgsConstructor
public class CourtReadDto extends ReadDtoBase<CourtBaseDto> {
    private SurfaceReadDto surface;
}
