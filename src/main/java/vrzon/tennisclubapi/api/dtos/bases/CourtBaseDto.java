package vrzon.tennisclubapi.api.dtos.bases;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourtBaseDto {
    @NotNull
    @Min(1)
    protected long number;
}
