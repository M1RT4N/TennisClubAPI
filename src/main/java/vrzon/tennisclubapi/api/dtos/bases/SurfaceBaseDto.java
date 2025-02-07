package vrzon.tennisclubapi.api.dtos.bases;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SurfaceBaseDto {
    @Size(min = 1, max = 20)
    protected String typeName;
    @Min(0)
    protected Float minutePrice;
}
