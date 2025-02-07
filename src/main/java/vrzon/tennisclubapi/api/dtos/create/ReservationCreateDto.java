package vrzon.tennisclubapi.api.dtos.create;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrzon.tennisclubapi.api.dtos.bases.ReservationBaseDto;

@Getter
@Setter
@NoArgsConstructor
public class ReservationCreateDto extends ReservationBaseDto {
    @NotNull
    @Min(1)
    private Long courtNumber;

    private MemberCreateDto member;
}
