package vrzon.tennisclubapi.api.dtos.read;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public abstract class ReadDtoBase<BaseDto> {
    protected Long id;
    protected boolean deleted;
    protected BaseDto data;
}
