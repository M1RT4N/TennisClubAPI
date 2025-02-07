package vrzon.tennisclubapi.api.dtos.bases;

import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberBaseDto {
    @NotNull
    @Size(min = 1, max = 50)
    protected String name;

    @NotNull
    @Size(min = 13, max = 13)
    protected String phoneNumber;
}
