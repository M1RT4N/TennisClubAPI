package vrzon.tennisclubapi.api.dtos.read;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vrzon.tennisclubapi.api.dtos.bases.ReservationBaseDto;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class ReservationReadDto extends ReadDtoBase<ReservationBaseDto> {
    private CourtReadDto court;

    private MemberReadDto member;

    private float totalPrice;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH:mm")
    private LocalDateTime createdAt;
}
