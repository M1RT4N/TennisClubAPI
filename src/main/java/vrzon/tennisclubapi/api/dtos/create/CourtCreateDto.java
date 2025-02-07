package vrzon.tennisclubapi.api.dtos.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrzon.tennisclubapi.api.dtos.bases.CourtBaseDto;

@Getter
@Setter
@NoArgsConstructor
public class CourtCreateDto extends CourtBaseDto {
    @NotNull
    @Min(1)
    private Long surfaceId;
}
